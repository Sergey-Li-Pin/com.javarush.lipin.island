package com.javarush.lipin.island.entity.location;

import com.javarush.lipin.island.entity.animals.Species;

import java.util.EnumMap;

public class Location {
    private int plants;
    private final EnumMap<Species, Integer> animals;

    public Location() {
        this.plants = 0;
        this.animals = new EnumMap<>(Species.class);
        for (Species s : Species.values()) {
            animals.put(s, 0);
        }
    }

    public int getPlants() {
        return plants;
    }

    public void setPlants(int plants) {
        this.plants = Math.max(0, plants);
    }

    public void addPlants(int delta, int maxPlants) {
        int next = plants + delta;
        if (next > maxPlants) { next = maxPlants; }
        if (next < 0) { next = 0; }
        plants = next;
    }

    public int getCount(Species species) {
        return animals.getOrDefault(species, 0);
    }

    public void setCount(Species species, int count) {
        animals.put(species, Math.max(0, count));
    }

    public EnumMap<Species, Integer> getAnimalsSnapshot() {
        return new EnumMap<>(animals);
    }
}
