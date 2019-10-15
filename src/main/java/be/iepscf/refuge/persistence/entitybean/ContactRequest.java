package be.iepscf.refuge.persistence.entitybean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="contact_requests")
public class ContactRequest {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(columnDefinition = "INT(11)")
	private Long id;

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private String phone;
	
	@Column
	private String message;
	
	@Column
	private Date date;
	
	@Column
	private boolean treated;
	
	@ManyToOne
	@JoinColumn(name="fk_animal")
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
