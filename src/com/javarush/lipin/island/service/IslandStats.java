package com.javarush.lipin.island.service;

import com.javarush.lipin.island.entity.animals.Species;

import java.util.EnumMap;

public class IslandStats {
    private final int tick;
    private final long plantsTotal;
    private final EnumMap<Species, Long> animalsTotal;
    private final long animalsTotalAll;

    public IslandStats(int tick, long plantsTotal, EnumMap<Species, Long> animalsTotal, long animalsTotalAll) {
        this.tick = tick;
        this.plantsTotal = plantsTotal;
        this.animalsTotal = animalsTotal;
        this.animalsTotalAll = animalsTotalAll;
    }

    public int getTick() { return tick; }
    public long getPlantsTotal() { return plantsTotal; }
    public long getAnimalsTotalAll() { return animalsTotalAll; }

    public long getAnimalsTotal(Species species) {
        return animalsTotal.getOrDefault(species, 0L);
    }
}
