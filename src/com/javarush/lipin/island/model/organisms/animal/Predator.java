package com.javarush.lipin.island.model.organisms.animal;

import com.javarush.lipin.island.config.Species;

public abstract class Predator extends Animal {

    protected Predator(Species species) {
        super(species);
    }
}
