package com.javarush.lipin.island.factory;

import com.javarush.lipin.island.config.Species;
import com.javarush.lipin.island.model.organisms.Plant;
import com.javarush.lipin.island.model.organisms.animal.Animal;
import com.javarush.lipin.island.model.organisms.animal.herbivores.*;
import com.javarush.lipin.island.model.organisms.animal.predators.*;

public final class OrganismFactory {

    private static final OrganismFactory INSTANCE = new OrganismFactory();

    private OrganismFactory() {
    }

    public static OrganismFactory getInstance() {
        return INSTANCE;
    }

    public Animal createAnimal(Species species) {
        if (species == null || !species.isAnimal()) {
            throw new IllegalArgumentException("Species must be an animal");
        }

        switch (species) {
            case WOLF:
                return new Wolf();
            case BOA:
                return new Boa();
            case FOX:
                return new Fox();
            case BEAR:
                return new Bear();
            case EAGLE:
                return new Eagle();

            case HORSE:
                return new Horse();
            case DEER:
                return new Deer();
            case RABBIT:
                return new Rabbit();
            case MOUSE:
                return new Mouse();
            case GOAT:
                return new Goat();
            case SHEEP:
                return new Sheep();
            case BOAR:
                return new Boar();
            case BUFFALO:
                return new Buffalo();
            case DUCK:
                return new Duck();
            case CATERPILLAR:
                return new Caterpillar();

            default:
                throw new IllegalArgumentException("Unsupported animal species: " + species);
        }
    }

    public Plant createPlant() {
        return new Plant();
    }
}
