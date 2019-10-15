package be.iepscf.refuge.dba;

import be.iepscf.refuge.business.util.PasswordManager;
import be.iepscf.refuge.persistence.dao.voldemort.*;
import be.iepscf.refuge.persistence.entitybean.*;
import be.iepscf.refuge.persistence.service.BeanService;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

/**
 *
 * dépendance à la couche business : PasswordManager
 * dépendance à la couche persistence : BeanService
 *
 */
public class DatabaseLoader {

    String[] extensions = {"jpg", "jpeg", "gif", "png"};
    protected BeanService beanService = new BeanService();
    protected PasswordManager passwordManager = new PasswordManager();


    public boolean hasRole(Long id) {
        return (beanService.getRole(id) != null);
    }

    public Role addRole(Long id, String name, String description) {
        Role role = new Role(id, name, description);
        beanService.saveRole(role);
        return role;
    }

    public User addUser(String firstName, String lastName, String email, String phone, String password, boolean active, Role role) {
        User user = new User(null, firstName, lastName, email, phone, null, null, active, role);
        passwordManager.setUserPassword(user, password);
        beanService.saveUser(user);
        return user;
    }

    public Species addSpecies(String name) {
        Species species = new Species(name);
        beanService.saveSpecies(species);
        return species;
    }

    public Race addRace(Species species, String name) {
        Race race = new Race(name, species);
        species.addRace(race);
        beanService.saveRace(race);
        return race;
    }

    public Color getColor(String name) {
        return beanService.getColorByName(name);
    }

    public Color addColor(String name) {
        Color color = new Color(name);
        beanService.saveColor(color);
        return color;
    }

    public Animal addAnimal(String name, String description, int birthYear, char sex, boolean sterilized, boolean adoptable, Species species, Race race, Color color) {
        Animal animal = new Animal(name, description);
        animal.setBirthYear(birthYear);
        animal.setSex(sex);
        animal.setSterilized(sterilized);
        animal.setAdoptable(adoptable);
        animal.setSpecies(species);
        animal.setRace(race);
        animal.setColor(color);
        loadAnimalPhotoIfExists(animal);
        beanService.saveAnimal(animal);
        return animal;
    }

    public ContactRequest addContactRequest(String firstName, String lastName, String email, String phone, String message, Animal animal) {
        ContactRequest contactRequest = new ContactRequest(null, firstName, lastName, email, phone, message,  new Date(), false, animal);
        animal.addContactRequest(contactRequest);
        beanService.saveContactRequest(contactRequest);
        return contactRequest;
    }








    public File getAnimalPhotosDir() {
        String userDirPath = System. getProperty("user.home");
        return new File(userDirPath + File.separator + "refuge-animals-photos");
    }

    public String getContentType(String extension) {
        String contentType = null;
        if (extension.equals("jpeg") || extension.equals("jpg")) {
            contentType = "image/jpeg";
        } else if (extension.contentEquals("png")) {
            contentType = "image/png";
        } else if (extension.contentEquals("gif")) {
            contentType = "image/gif";
        }
        return contentType;
    }

    public void loadAnimalPhotoIfExists(be.iepscf.refuge.persistence.entitybean.Animal animal) {
        for (String extension : extensions) {
            File file = new File(getAnimalPhotosDir(), animal.getName().toLowerCase() + "." + extension);
            System.out.println("testing photo file : " + file.getPath());
            if (file.exists()) {
                byte[] photo = new byte[(int) file.length()];
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    fileInputStream.read(photo);
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String photoContentType = getContentType(extension);
                int photoContentLength = photo.length;
                if (photoContentLength > 0 && photoContentType != null) {
                    animal.setPhoto(photo);
                    animal.setPhotoContentType(photoContentType);
                    animal.setPhotoContentLength(photoContentLength);
                }
            }
        }
    }






    public void empty() {
        VoldemortDAOFactory daoFactory = new VoldemortDAOFactory();
        ((VoldemortUserDAO) daoFactory.getUserDAO()).empty(true);
        ((VoldemortRoleDAO) daoFactory.getRoleDAO()).empty();
        ((VoldemortContactRequestDAO) daoFactory.getContactRequestDAO()).empty(true);
        ((VoldemortAnimalDAO) daoFactory.getAnimalDAO()).empty(true);
        ((VoldemortColorDAO) daoFactory.getColorDAO()).empty(true);
        ((VoldemortRaceDAO) daoFactory.getRaceDAO()).empty(true);
        ((VoldemortSpeciesDAO) daoFactory.getSpeciesDAO()).empty(true);
    }

    public void load() {
        DatabaseXMLParser loader = new DatabaseXMLParser();
        loader.load();
    }

    public void show() {
        for (Role role : beanService.getRoles()) {
            System.out.println(role);
        }
        for (User user : beanService.getUsers()) {
            System.out.println(user);
        }
        for (Species species : beanService.getSpecies()) {
            System.out.println(species);
            for (Race race : species.getRaces()) {
                System.out.println("\t" + race);

            }
        }
        for (Color color : beanService.getColors()) {
            System.out.println(color);
        }
        for (Animal animal : beanService.getAnimals()) {
            System.out.println(animal);
        }
        for (ContactRequest contactRequest : beanService.getContactRequests()) {
            System.out.println(contactRequest);
        }
    }


    public void reset() {
        System.out.println("before empty");
        empty();
        System.out.println("before load");
        load();
        System.out.println("before show");
        show();
    }

    public static void main(String[] args) {
        DatabaseLoader manager = new DatabaseLoader();
        manager.reset();
    }

}
