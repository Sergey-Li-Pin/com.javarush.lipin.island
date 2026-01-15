package com.javarush.lipin.island.service;

import com.javarush.lipin.island.entity.animals.Species;
import com.javarush.lipin.island.entity.island.Island;
import com.javarush.lipin.island.entity.location.Location;

import java.util.EnumMap;

public class StatisticsService {

    public IslandStats calculate(Island island, int tick) {
        long plantsTotal = 0;

        EnumMap<Species, Long> animalsTotal = new EnumMap<>(Species.class);
        for (Species s : Species.values()) {
            animalsTotal.put(s, 0L);
        }

        for (Location[] row : island.getLocations()) {
            for (Location cell : row) {
                plantsTotal += cell.getPlants();

                for (Species s : Species.values()) {
                    long prev = animalsTotal.get(s);
                    animalsTotal.put(s, prev + cell.getCount(s));
                }
            }
        }

        long animalsAll = 0;
        for (Species s : Species.values()) {
            animalsAll += animalsTotal.get(s);
        }

        return new IslandStats(tick, plantsTotal, animalsTotal, animalsAll);
    }
}
