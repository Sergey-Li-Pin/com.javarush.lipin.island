package com.javarush.lipin.island.model.organisms;

import com.javarush.lipin.island.config.Species;

public abstract class Organism implements Eatable {

    private final Species species;

    protected Organism(Species species) {
        if (species == null) {
            throw new IllegalArgumentException("Species is null");
        }
        this.species = species;
    }

    @Override
    public Species getSpecies() {
        return species;
    }

    @Override
    public double getWeight() {
        return species.getWeight();
    }

    public String getIcon() {
        return species.getIcon();
    }

    public String getTitle() {
        return species.getTitle();
    }
}
