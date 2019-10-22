package be.iepscf.refuge.business.service;
import be.iepscf.refuge.business.businessbean.*;
import be.iepscf.refuge.business.servlet.exception.UnconfirmedPasswordException;
import be.iepscf.refuge.business.util.PasswordManager;

import java.util.List;

/**
 * méthodes nécessaires à
 */
public class GestionService extends PublicService {



	/* Role : */

	public Role getRole(Long id) {
		return getModelService().getRole(id);
	}

	public List<Role> getRoles() {
		return getModelService().getRoles();
	}



	/* User : */

	public User getUser(Long id) {
		return getModelService().getUser(id);
	}

	public List<User> getUsers() {
		return getModelService().getUsers();
	}

	public User addUser(String firstName, String lastName, String email, String phone, String password, String confirm) throws UnconfirmedPasswordException {
		Role employe = getRole(1L);
		User user = new User(null, firstName, lastName, email, phone, null, null, true, employe);
		password = password.trim();
		confirm = confirm.trim();
		// + verif nulls etc
		if (! password.equals(confirm)) {
			throw new UnconfirmedPasswordException();
		}
		getPasswordManager().setUserPassword(user, password);
		long lastInsertId = getModelService().saveUser(user);
		return user;
	}

	public User updateUser(Long id, String firstName, String lastName, String email, String phone) {
		User user = getUser(id);
		if (user == null) {
			return null;
		}
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		long affectedRows = getModelService().updateUser(user);
		return user;
	}

	public User activateUser(Long id) {
		User user = getUser(id);
		user.setActive(true);
		long affectedRows = getModelService().updateUser(user);
		return user;
	}

	public User deactivateUser(Long id) {
		User user = getUser(id);
		user.setActive(false);
		long affectedRows = getModelService().updateUser(user);
		return user;
	}

	public long deleteUser(Long id) {
		User user = getUser(id);
		return getModelService().deleteUser(user);
	}


	//Animal:

	public Animal getAnimal (Long id){
		return getModelService().getAnimal(id);
	}

	public List<Animal> getAnimals(){
		return getModelService().getAnimals();
	}

	public Animal addAnimal (String name, String description, int birthYear, String sex, boolean sterilized, boolean adoptable, String photoContentType, int photoContentLength, String photoContent, String species, String race, String color){
		Animal animal= new Animal(name, description, birthYear, sex, sterilized, adoptable, photoContentType, photoContentLength, photoContent, species, race, color);
		long lastInsertID = getModelService().saveAnimal(animal);
		return animal;
	}

	public Animal updateAnimal(Long id, String name, String description, int birthYear, char sex, boolean sterilized, boolean adoptable, String photoContentType, int photoContentLength, byte[] photoContent, Species species, Race race, Color color){
		Animal animal = getAnimal(id);
		if (animal == null) {
			return null;
		}

		animal.setName(name);
		animal.setDescription(description);
		animal.setBirthYear(birthYear);
		animal.setSex(sex);
		animal.setSterilized(sterilized);
		animal.setAdoptable(adoptable);
		animal.setPhotoContentType(photoContentType);
		animal.setPhotoContentLength(photoContentLength);
		animal.setPhotoContent(photoContent);
		animal.setSpecies(species);
		animal.setRace(race);
		animal.setColor(color);

		long affectedRows = getModelService().updateAnimal(animal);
		return animal;

	}

	public long deleteAnimal(Long id){
		Animal animal= getAnimal(id);
		return getModelService().deleteAnimal(animal);
	}







	public List<ContactRequest> getContactRequestsByAnimal(Animal animal) {
		return getModelService().getContactRequestsByAnimal(animal);
	}

	public ContactRequest setContactRequestAsTreated(Long id) {
		ContactRequest contactRequest = getContactRequests(id);
		contactRequest.setTreated(true);
		long affectedRows = getModelService().updateContactRequest(contactRequest);
		return contactRequest;
	}

	public ContactRequest updateContactRequest(Long id, String firstName, String lastName, String email, String phone, String message, boolean treated){
		ContactRequest contactRequest = getContactRequests(id);
		if (contactRequest == null) {
			return null;
		}
		contactRequest.setFirstName(firstName);
		contactRequest.setLastName(lastName);
		contactRequest.setEmail(email);
		contactRequest.setPhone(phone);
		contactRequest.setMessage(message);
		contactRequest.setTreated(treated);

		long affectedRows = getModelService().updateContactRequest(contactRequest);
		return contactRequest;
	}

	public ContactRequest updateContactRequestTreated(Long id, boolean treated){
		ContactRequest cr = getContactRequest(id);
		cr.setTreated(treated);
		long affectedRows = getModelService().updateContactRequest(cr);
		return cr;
	}



	public ContactRequest getContactRequest(Long id) {
		return getModelService().getContactRequest(id);
	}

	public List<ContactRequest> geContactRequest() {
		return getModelService().getContactRequest();
	}

}
