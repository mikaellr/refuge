package be.iepscf.refuge.business.service;
import be.iepscf.refuge.business.businessbean.ContactRequest;
import be.iepscf.refuge.business.businessbean.Role;
import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.util.PasswordManager;

import java.util.List;


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

	public User addUser(String firstName, String lastName, String email, String phone, String password, String confirm) {
		Role employe = getRole(1L);
		User user = new User(null, firstName, lastName, email, phone, null, null, true, employe);
		password = password.trim();
		confirm = confirm.trim();
		// + verif nulls etc
		if (! password.equals(confirm)) {
			System.err.println("erreur password confirm");
			return null;
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










	public ContactRequest setContactRequestAsTreated(Long id) {
		ContactRequest contactRequest = getContactRequests(id);
		contactRequest.setTreated(true);
		long affectedRows = getModelService().updateContactRequest(contactRequest);
		return contactRequest;
	}

}
