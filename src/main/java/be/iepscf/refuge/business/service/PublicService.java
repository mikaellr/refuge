package be.iepscf.refuge.business.service;

import be.iepscf.refuge.business.businessbean.*;
import be.iepscf.refuge.business.util.PasswordManager;

import java.util.Date;
import java.util.List;

/**
 * méthodes nécessaires aux actions "public" ou "visiteur"  (non connecté)
 *
 * à appeler depuis les servlets
 */
public class PublicService {

	private ModelService _modelService = new ModelService();
	private PasswordManager _passwordManager;

	protected PasswordManager getPasswordManager() {
		if (_passwordManager == null) {
			_passwordManager = new PasswordManager();
		}
		return _passwordManager;
	}

	protected ModelService getModelService() {
		return _modelService;
	}






	public User login(String email, String password)  {
		User user = getModelService().getUserByEmail(email);
		if (user == null) {
			System.err.println("login: user not found by email");
		}
		if (getPasswordManager().checkUserPassword(user, password)) {
			return user;
		} else {
			System.err.println("login: password check failed");
		}
		return null;
	}




	public Species getSpecies(Long id) {
		return getModelService().getSpecies(id);
	}

	public Species getSpecies(String name) {
		return getModelService().getSpecies(name);
	}

	public List<Species> getSpecies() {
		return getModelService().getSpecies();
	}



	public Race getRace(Long id) {
		return getModelService().getRace(id);
	}

	public List<Race> getRaces() {
		return getModelService().getRaces();
	}

	public List<Race> getRacesBySpecies(Species species) {
		return getModelService().getRacesBySpecies(species);
	}



	public List<Color> getColors() {
		return getModelService().getColors();
	}




	public Animal getAnimal(Long id) {
		return getModelService().getAnimal(id);
	}

	public List<Animal> getAnimals() {
		return getModelService().getAnimals();
	}

	public List<Animal> getAnimalsQuery(Species species, Race race, Long offset, Long limit, Boolean last, Boolean adoptable, Boolean all) {
		return getModelService().getAnimalsQuery(species, race, offset, limit, last, adoptable, all);
	}

	public List<Animal> getAnimalsQuery(Long species, Long race, Long offset, Long limit, Boolean last, Boolean adoptable, Boolean all) {
		return getModelService().getAnimalsQuery(species, race, offset, limit, last, adoptable, all);
	}






	public ContactRequest getContactRequests(Long id) {
        return getModelService().getContactRequest(id);
    }

    public List<ContactRequest> getContactRequests() {
        return getModelService().getContactRequests();
    }

	public ContactRequest addContactRequest(String firstName, String lastName, String email, String phone, String message, long animalId) {
		Animal animal = getAnimal(animalId);
		if (animal == null) {
			System.err.println("animal not found");
			// créer des exceptions...
			return null;
		}
		ContactRequest contactRequest = new ContactRequest(null, firstName, lastName, email, phone, message, new Date(), false, animal);
		long lastInsertId = getModelService().saveContactRequest(contactRequest);
		return contactRequest;
	}
}
