package be.iepscf.refuge.business.businessbean;

public class Color {
	
	private Long id;

	private String name;

	public Color() {
	}
	
	public Color(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Color(String name) {
		this(null, name);
	}

	public String toString() {
		return "Color#" + id + " (name=" + name + ")";
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
	
}
