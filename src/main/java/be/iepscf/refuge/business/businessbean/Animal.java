package be.iepscf.refuge.business.businessbean;

import java.util.ArrayList;
import java.util.List;

public class Animal {

	private Long id;

	private String name;
	
	private String description;
	
	private int birthYear;
	
	private char sex;
	
	private boolean sterilized;
	
	private boolean adoptable = true;
	
	private String photoContentType;
	
	private int photoContentLength;
	
	private byte[] photoContent;
	
	private Species species;

	private Race race;

	private Color color;


	private List<ContactRequest> contactRequests = new ArrayList<ContactRequest>();

	public Animal() {
	}

	public Animal(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Animal(String name, String description) {
		this(null, name, description);
	}

	public Animal(String name) {
		this(name, null);
	}



	public Animal(String name, String description, int birthYear, String sex, boolean sterilized, boolean adoptable, String photoContentType, int photoContentLength, String photoContent, String species, String race, String color) {
	}

	public String toString() {
		return "Animal#" + id + " (name=" + name + ", description=" + description
			+ ", species=" + (species != null ? species.getName() : null)
			+ ", race=" + (race != null ? race.getName() : null)
			+ ", color=" + (color != null ? color.getName() : null)
			+ ", adoptable=" + adoptable + ", sterilized=" + sterilized
			+ ", photo=" + (photoContent != null ? photoContent.length : null) + ", photoContentType=" + photoContentType + ", photoContentLength=" + photoContentLength
			+ ", contactRequets=" + contactRequests.size()
			+ ")";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public boolean isSterilized() {
		return sterilized;
	}
	public void setSterilized(boolean sterilized) {
		this.sterilized = sterilized;
	}
	public boolean isAdoptable() {
		return adoptable;
	}
	public void setAdoptable(boolean adoptable) {
		this.adoptable = adoptable;
	}
	public String getPhotoContentType() {
		return photoContentType;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	public int getPhotoContentLength() {
		return photoContentLength;
	}
	public void setPhotoContent( byte[] photoContent) {
		this.photoContent=photoContent;
	}
	public void setPhotoContentLength(int photoContentLength) {
		this.photoContentLength = photoContentLength;
	}
	public byte[] getPhoto() {
		return photoContent;
	}
	public boolean hasPhoto() {
		if (photoContent != null && photoContent.length > 0 && photoContentType != null && photoContentType.length() > 0) {
			return true;
		}
		return false;
	}
	public void setPhoto(byte[] photo) {
		this.photoContent = photo;
	}
	public Species getSpecies() {
		return species;
	}
	public void setSpecies(Species species) {
		this.species = species;
	}
	public Race getRace() {
		return race;
	}
	public void setRace(Race race) {
		this.race = race;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public List<ContactRequest> getContactRequests() {
		return contactRequests;
	}

	public void setContactRequests(List<ContactRequest> contactRequests) {
		this.contactRequests = contactRequests;
	}
	
	public void addContactRequest(ContactRequest contactRequests) {
		this.contactRequests.add(contactRequests);
	}
	
}
