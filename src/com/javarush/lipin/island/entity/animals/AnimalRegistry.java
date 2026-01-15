package com.javarush.lipin.island.entity.animals;

import java.util.EnumMap;

public final class AnimalRegistry {

    private static final EnumMap<Species, Animal> BY_SPECIES = new EnumMap<>(Species.class);

    static {
        BY_SPECIES.put(Species.WOLF, new Wolf());
        BY_SPECIES.put(Species.BOA, new Boa());
        BY_SPECIES.put(Species.FOX, new Fox());
        BY_SPECIES.put(Species.BEAR, new Bear());
        BY_SPECIES.put(Species.EAGLE, new Eagle());

        BY_SPECIES.put(Species.HORSE, new Horse());
        BY_SPECIES.put(Species.DEER, new Deer());
        BY_SPECIES.put(Species.RABBIT, new Rabbit());
        BY_SPECIES.put(Species.MOUSE, new Mouse());
        BY_SPECIES.put(Species.GOAT, new Goat());
        BY_SPECIES.put(Species.SHEEP, new Sheep());
        BY_SPECIES.put(Species.BOAR, new Boar());
        BY_SPECIES.put(Species.BUFFALO, new Buffalo());
        BY_SPECIES.put(Species.DUCK, new Duck());
        BY_SPECIES.put(Species.CATERPILLAR, new Caterpillar());
    }

    private AnimalRegistry() {}

    public static Animal get(Species species) {
        return BY_SPECIES.get(species);
    }
}
