package be.iepscf.refuge.business.service;
import be.iepscf.refuge.business.businessbean.Role;
import be.iepscf.refuge.business.businessbean.User;
import be.iepscf.refuge.business.util.PasswordManager;



public class EmployeService extends PublicService {

	PasswordManager passwordManager;

	protected PasswordManager getPasswordManager() {
		if (passwordManager == null) {
			passwordManager = new PasswordManager();
		}
		return passwordManager;
	}


	public User login(String email, String password)  {
		User user = getUserByEmail(email);
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

	public User getUserByEmail(String email) {
		return  conv(getBeanService().getUserByEmail(email));
	}







	/* User : accès en écriture */

	public long saveUser(User user) {
		return getWebServiceClientService().saveUser(user);
		//return getBeanService().saveUser(conv(user));
	}

	public long updateUser(User user) {
		return getWebServiceClientService().updateUser(user);
		//return getBeanService().updateUser(conv(user));
	}

	public long deleteUser(User user) {
		return getWebServiceClientService().deleteUser(user);
		//return getBeanService().deleteUser(conv(user));
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
		long lastInsertId = saveUser(user);
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
		long affectedRows = updateUser(user);
		return user;
	}

	public User activateUser(Long id) {
		User user = getUser(id);
		user.setActive(true);
		long affectedRows = updateUser(user);
		return user;
	}

	public User deactivateUser(Long id) {
		User user = getUser(id);
		user.setActive(false);
		long affectedRows = updateUser(user);
		return user;
	}

	public long deleteUser(Long id) {
		User user = getUser(id);
		return deleteUser(user);
	}

}
