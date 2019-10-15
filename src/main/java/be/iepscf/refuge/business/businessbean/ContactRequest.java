package be.iepscf.refuge.business.businessbean;

import java.util.Date;

public class ContactRequest {

	private Long id;

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phone;
	
	private String message;
	
	private Date date;
	
	private boolean treated;
	
	private Animal animal;


	public ContactRequest() {
	}

	public ContactRequest(Long id, String firstName, String lastName, String email, String phone, String message,
			Date date, boolean treated, Animal animal) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.message = message;
		this.date = date;
		this.treated = treated;
		this.animal = animal;
	}

	public String toString() {
		return "ContactRequest#" + id + " (firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone
			+ ", message=" + message
			+ ", date=" + date
			+ ", treated=" + treated
			+ ", animal=" + (animal != null ? animal.getName() : null)
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



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public boolean isTreated() {
		return treated;
	}



	public void setTreated(boolean treated) {
		this.treated = treated;
	}



	public Animal getAnimal() {
		return animal;
	}



	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
		
}
