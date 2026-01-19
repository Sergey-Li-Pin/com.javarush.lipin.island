package com.javarush.lipin.island.model.organisms.animal;

import com.javarush.lipin.island.config.Species;

public abstract class Herbivore extends Animal {

    protected Herbivore(Species species) {
        super(species);
    }
}
