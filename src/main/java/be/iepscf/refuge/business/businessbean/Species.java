package be.iepscf.refuge.business.businessbean;

import java.util.ArrayList;
import java.util.List;

public class Species {

	private Long id;
	
	private String name;

	private List<Race> races = new ArrayList<Race>();

	private List<Animal> animals = new ArrayList<Animal>();

	public Species() {
	}

	public Species(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Species(String name) {
		this(null, name);
	}

	public String toString() {
		return "Species#" + id + " (name=" + name + ")";
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

	public List<Race> getRaces() {
		return races;
	}

	public void setRaces(List<Race> races) {
		this.races = races;
	}

	public void addRace(Race race) {
		this.races.add(race);
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
