package com.javarush.lipin.island.entity.animals;

public abstract class Animal {
    private final Species species;

    protected Animal(Species species) {
        this.species = species;
    }

    public Species getSpecies() {
        return species;
    }

    public String getEmoji() {
        return species.getEmoji();
    }
}
