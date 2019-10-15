package be.iepscf.refuge.business.businessbean;

public class User {
	
	public static boolean SHOW_SALT_AND_HASH_IN_TOSTRING = true;

	private Long id;

	private String firstName;
	
	private String lastName;

	private String email;
	
	private String phone;
	
	private String hash;
	
	private String salt;
	
	private boolean active = true;
	
	private Role role;


	public User() {
	}

	public User(Long id, String firstName, String lastName, String email, String phone, String hash, String salt,
			boolean active, Role role) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.hash = hash;
		this.salt = salt;
		this.active = active;
		this.role = role;
	}

	public String toString() {
		return "User#" + id + " (firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
			+ ", active=" + active
			+ (SHOW_SALT_AND_HASH_IN_TOSTRING ? String.format(", salt='%s', hash='%s'", salt, hash) : null)
			+ ", role=" + (role != null ? role.getName() : null)
			+ ")";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
