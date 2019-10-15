package be.iepscf.refuge.business.service;

import be.iepscf.refuge.business.businessbean.*;
import be.iepscf.refuge.persistence.service.BeanService;

import java.util.ArrayList;
import java.util.List;


/***
 * @TODO supprimer le couplage à la couche persistence (BeanService)
 */
public class PublicService {

	private BeanService beanService;
	private WebServiceClientService webServiceClientService;

	public BeanService getBeanService() {
		if (beanService == null) {
			beanService = new BeanService();
		}
		return beanService;
	}

	public WebServiceClientService getWebServiceClientService() {
		if (webServiceClientService == null) {
			webServiceClientService = new WebServiceClientService();
		}
		return webServiceClientService;
	}





	/* conversion des EntityBeans en Businessbeans et vice-versa */

	public Role conv(be.iepscf.refuge.persistence.entitybean.Role src) {
		if (src == null) return null;
		return new Role(src.getId(), src.getName(), src.getDescription());
	}

	public be.iepscf.refuge.persistence.entitybean.Role conv(Role src) {
		if (src == null) return null;
		return new be.iepscf.refuge.persistence.entitybean.Role(src.getId(), src.getName(), src.getDescription());
	}

	public User conv(be.iepscf.refuge.persistence.entitybean.User src) {
		if (src == null) return null;
		return new User(src.getId(), src.getFirstName(), src.getLastName(), src.getEmail(), src.getPhone(), src.getHash(), src.getSalt(), src.isActive(), conv(src.getRole()));
	}

	public be.iepscf.refuge.persistence.entitybean.User conv(User src) {
		if (src == null) return null;
		return new be.iepscf.refuge.persistence.entitybean.User(src.getId(), src.getFirstName(), src.getLastName(), src.getEmail(), src.getPhone(), src.getHash(), src.getSalt(), src.isActive(), conv(src.getRole()));
	}

	public List<User> convUsers(List<be.iepscf.refuge.persistence.entitybean.User> srcs) {
		if (srcs == null) return null;
		List<User> items = new ArrayList<User>();
		for (be.iepscf.refuge.persistence.entitybean.User src : srcs) {
			items.add(conv(src));
		}
		return items;
	}

	public Species conv(be.iepscf.refuge.persistence.entitybean.Species src) {
		if (src == null) return null;
		return new Species(src.getId(), src.getName());
	}

	public be.iepscf.refuge.persistence.entitybean.Species conv(Species src) {
		if (src == null) return null;
		return new be.iepscf.refuge.persistence.entitybean.Species(src.getId(), src.getName());
	}

	public List<Species> convSpecies(List<be.iepscf.refuge.persistence.entitybean.Species> srcs) {
		if (srcs == null) return null;
		List<Species> items = new ArrayList<Species>();
		for (be.iepscf.refuge.persistence.entitybean.Species src : srcs) {
			items.add(conv(src));
		}
		return items;
	}

	public Race conv(be.iepscf.refuge.persistence.entitybean.Race src) {
		if (src == null) return null;
		return new Race(src.getId(), src.getName(), conv(src.getSpecies()));
	}

	public be.iepscf.refuge.persistence.entitybean.Race conv(Race src) {
		if (src == null) return null;
		return new be.iepscf.refuge.persistence.entitybean.Race(src.getId(), src.getName(), conv(src.getSpecies()));
	}

	public List<Race> convRaces(List<be.iepscf.refuge.persistence.entitybean.Race> srcs) {
		if (srcs == null) return null;
		List<Race> items = new ArrayList<Race>();
		for (be.iepscf.refuge.persistence.entitybean.Race src : srcs) {
			items.add(conv(src));
		}
		return items;
	}

	public Color conv(be.iepscf.refuge.persistence.entitybean.Color src) {
		if (src == null) return null;
		return new Color(src.getId(), src.getName());
	}

	public be.iepscf.refuge.persistence.entitybean.Color conv(Color src) {
		if (src == null) return null;
		return new be.iepscf.refuge.persistence.entitybean.Color(src.getId(), src.getName());
	}

	public List<Color> convColor(List<be.iepscf.refuge.persistence.entitybean.Color> srcs) {
		if (srcs == null) return null;
		List<Color> items = new ArrayList<Color>();
		for (be.iepscf.refuge.persistence.entitybean.Color src : srcs) {
			items.add(conv(src));
		}
		return items;
	}

	public Animal conv(be.iepscf.refuge.persistence.entitybean.Animal src) {
		if (src == null) return null;
		Animal item = new Animal();
		item.setId(src.getId());
		item.setName(src.getName());
		item.setDescription(src.getDescription());
		item.setBirthYear(src.getBirthYear());
		item.setSex(src.getSex());
		item.setSterilized(src.isSterilized());
		item.setPhotoContentType(src.getPhotoContentType());
		item.setPhotoContentLength(src.getPhotoContentLength());
		item.setPhoto(src.getPhoto());
		item.setSpecies(conv(src.getSpecies()));
		item.setRace(conv(src.getRace()));
		item.setColor(conv(src.getColor()));
		return item;
	}

	public be.iepscf.refuge.persistence.entitybean.Animal conv(Animal src) {
		if (src == null) return null;
		be.iepscf.refuge.persistence.entitybean.Animal item = new be.iepscf.refuge.persistence.entitybean.Animal();
		item.setId(src.getId());
		item.setName(src.getName());
		item.setDescription(src.getDescription());
		item.setBirthYear(src.getBirthYear());
		item.setSex(src.getSex());
		item.setSterilized(src.isSterilized());
		item.setPhotoContentType(src.getPhotoContentType());
		item.setPhotoContentLength(src.getPhotoContentLength());
		item.setPhoto(src.getPhoto());
		item.setSpecies(conv(src.getSpecies()));
		item.setRace(conv(src.getRace()));
		item.setColor(conv(src.getColor()));
		return item;
	}

	public List<Animal> convAnimals(List<be.iepscf.refuge.persistence.entitybean.Animal> srcs) {
		if (srcs == null) return null;
		List<Animal> items = new ArrayList<Animal>();
		for (be.iepscf.refuge.persistence.entitybean.Animal src : srcs) {
			items.add(conv(src));
		}
		return items;
	}


	public ContactRequest conv(be.iepscf.refuge.persistence.entitybean.ContactRequest src) {
		if (src == null) return null;
		return new ContactRequest(src.getId(), src.getFirstName(), src.getLastName(), src.getEmail(), src.getPhone(), src.getMessage(), src.getDate(), src.isTreated(), conv(src.getAnimal()));
	}

	public be.iepscf.refuge.persistence.entitybean.ContactRequest conv(ContactRequest src) {
		if (src == null) return null;
		return new be.iepscf.refuge.persistence.entitybean.ContactRequest(src.getId(), src.getFirstName(), src.getLastName(), src.getEmail(), src.getPhone(), src.getMessage(), src.getDate(), src.isTreated(), conv(src.getAnimal()));
	}

	public List<ContactRequest> convContactRequests(List<be.iepscf.refuge.persistence.entitybean.ContactRequest> srcs) {
		if (srcs == null) return null;
		List<ContactRequest> items = new ArrayList<ContactRequest>();
		for (be.iepscf.refuge.persistence.entitybean.ContactRequest src : srcs) {
			items.add(conv(src));
		}
		return items;
	}














	/* User : accès en lecture */

	public User getUser(Long id) {
		return getWebServiceClientService().getUser(id);
	}

	public List<User> getUsers() {
		return getWebServiceClientService().getUsers();
	}












	public Role getRole(Long id) {
		return conv(getBeanService().getRole(id));
	}

	public List<Role> getRoles() {
		return null;
	}



	public Species getSpecies(Long id) {
		return conv(getBeanService().getSpecies(id));
	}

	public Species getSpecies(String name) {
		return conv(getBeanService().getSpeciesByName(name));
	}

	public List<Species> getSpecies() {
		return convSpecies(getBeanService().getSpecies());
	}



	public Race getRace(Long id) {
		return conv(getBeanService().getRace(id));
	}

	public List<Race> getRaces() {
		return convRaces(getBeanService().getRaces());
	}




	public List<Color> getColors() {
		return convColor(getBeanService().getColors());
	}




	public Animal getAnimal(Long id) {
		return conv(getBeanService().getAnimal(id));
	}

	public List<Animal> getAnimals() {
		return convAnimals(getBeanService().getAnimals());
	}





    public ContactRequest getContactRequests(Long id) {
        return conv(beanService.getContactRequest(id));
    }

    public List<ContactRequest> getContactRequests() {
        return convContactRequests(beanService.getContactRequests());
    }

}
