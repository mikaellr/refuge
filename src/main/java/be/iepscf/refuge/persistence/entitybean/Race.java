package be.iepscf.refuge.persistence.entitybean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="races")
public class Race {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(columnDefinition = "INT(11)")
	private Long id;
	
	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name="fk_species")
	private Species species;
	
	@OneToMany(mappedBy="race")
	private List<Animal> animals = new ArrayList<Animal>();

	public Race() {
	}

	public Race(Long id, String name, Species species) {
		this.id = id;
		this.name = name;
		this.species = species;
	}

	public Race(String name, Species species) {
		this(null, name, species);
	}

	public String toString() {
		return "Races#" + id + " (name=" + name + ", species=" + (species != null ? species.getName() : null) + ")";
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

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}
	
	public void addAnimal(Animal animal) {
		this.animals.add(animal);
	}
	
}
