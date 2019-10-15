package be.iepscf.refuge.business.businessbean;

import java.util.ArrayList;
import java.util.List;

public class Race {

	private Long id;
	
	private String name;

	private Species species;

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
		return "Races#" + id + " (name=" + name + ", species=" + species.getName() + ")";
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
