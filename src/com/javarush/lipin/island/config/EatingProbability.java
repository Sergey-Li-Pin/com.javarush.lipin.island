package com.javarush.lipin.island.config;

import java.util.EnumMap;
import java.util.Map;

public final class EatingProbability {

    private EatingProbability() {
    }

    private static final Species[] FOODS_ORDER = {
            Species.WOLF, Species.BOA, Species.FOX, Species.BEAR, Species.EAGLE,
            Species.HORSE, Species.DEER, Species.RABBIT, Species.MOUSE,
            Species.GOAT, Species.SHEEP, Species.BOAR, Species.BUFFALO,
            Species.DUCK, Species.CATERPILLAR, Species.PLANT
    };

    private static final Map<Species, Map<Species, Integer>> TABLE = new EnumMap<>(Species.class);

    static {
        putRow(Species.WOLF,
                0, 0, 0, 0, 0,
                10, 15, 60, 80,
                60, 70, 15, 10,
                40, 0, 0
        );

        putRow(Species.BOA,
                0, 0, 15, 0, 0,
                0, 0, 20, 40,
                0, 0, 0, 0,
                10, 0, 0
        );

        putRow(Species.FOX,
                0, 0, 0, 0, 0,
                0, 0, 70, 90,
                0, 0, 0, 0,
                60, 40, 0
        );

        putRow(Species.BEAR,
                0, 80, 0, 0, 0,
                40, 80, 80, 90,
                70, 70, 50, 20,
                10, 0, 0
        );

        putRow(Species.EAGLE,
                0, 0, 10, 0, 0,
                0, 0, 90, 90,
                0, 0, 0, 0,
                80, 0, 0
        );

        putRow(Species.HORSE,
                0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 100
        );

        putRow(Species.DEER,
                0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 100
        );

        putRow(Species.RABBIT,
                0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 100
        );

        putRow(Species.MOUSE,
                0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 90, 100
        );

        putRow(Species.GOAT,
                0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 100
        );

        putRow(Species.SHEEP,
                0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 100
        );

        putRow(Species.BOAR,
                0, 0, 0, 0, 0,
                0, 0, 0, 50,
                0, 0, 0, 0,
                0, 90, 100
        );

        putRow(Species.BUFFALO,
                0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 100
        );

        putRow(Species.DUCK,
                0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 90, 100
        );

        putRow(Species.CATERPILLAR,
                0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 100
        );
    }

    private static void putRow(Species eater, int... chances) {
        if (chances.length != FOODS_ORDER.length) {
            throw new IllegalArgumentException("Row length must be " + FOODS_ORDER.length + ", but was " + chances.length);
        }

        Map<Species, Integer> row = new EnumMap<>(Species.class);
        for (int i = 0; i < FOODS_ORDER.length; i++) {
            row.put(FOODS_ORDER[i], chances[i]);
        }
        TABLE.put(eater, row);
    }

    public static int getChance(Species eater, Species food) {
        Map<Species, Integer> row = TABLE.get(eater);
        if (row == null) {
            return 0;
        }
        Integer chance = row.get(food);
        return chance == null ? 0 : chance;
    }
}
