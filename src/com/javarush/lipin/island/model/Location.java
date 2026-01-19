package com.javarush.lipin.island.model;

import com.javarush.lipin.island.config.Species;
import com.javarush.lipin.island.model.organisms.animal.Animal;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Location {

    private final Position position;

    private int plantCount;

    private final Map<Species, List<Animal>> animalsBySpecies = new EnumMap<>(Species.class);

    public Location(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public int getPlantCount() {
        return plantCount;
    }

    public void setPlantCount(int plantCount) {
        this.plantCount = Math.max(0, plantCount);
    }

    public void addPlants(int count) {
        if (count <= 0) {
            return;
        }
        int max = Species.PLANT.getMaxPerCell();
        this.plantCount = Math.min(max, this.plantCount + count);
    }

    public int removePlants(int count) {
        if (count <= 0 || plantCount <= 0) {
            return 0;
        }
        int removed = Math.min(count, plantCount);
        plantCount -= removed;
        return removed;
    }

    public List<Animal> getAnimals(Species species) {
        if (species == null || !species.isAnimal()) {
            throw new IllegalArgumentException("Species must be animal");
        }
        return animalsBySpecies.computeIfAbsent(species, s -> new ArrayList<>());
    }

    public int getAnimalsCount(Species species) {
        return getAnimals(species).size();
    }

    public boolean addAnimal(Animal animal) {
        if (animal == null || !animal.isAlive()) {
            return false;
        }
        Species species = animal.getSpecies();
        List<Animal> list = getAnimals(species);
        if (list.size() >= species.getMaxPerCell()) {
            return false;
        }
        list.add(animal);
        return true;
    }

    public boolean removeAnimal(Animal animal) {
        if (animal == null) {
            return false;
        }
        Species species = animal.getSpecies();
        List<Animal> list = animalsBySpecies.get(species);
        if (list == null) {
            return false;
        }
        return list.remove(animal);
    }

    public boolean containsAnimal(Animal animal) {
        if (animal == null) {
            return false;
        }
        List<Animal> list = animalsBySpecies.get(animal.getSpecies());
        return list != null && list.contains(animal);
    }
}
