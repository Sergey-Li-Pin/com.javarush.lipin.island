package com.javarush.lipin.island.service;

import com.javarush.lipin.island.config.SimulationConfig;
import com.javarush.lipin.island.entity.animals.Species;
import com.javarush.lipin.island.entity.island.Island;
import com.javarush.lipin.island.entity.location.Location;
import com.javarush.lipin.island.util.RandomUtil;

public class IslandInitializer {

    public void populate(Island island, SimulationConfig config) {
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location cell = island.getLocation(x, y);

                // Растения
                int plants = calcRandomInitialCount(config.getPlantsMaxPerCell(), config.getInitialPlantsFillFactor());
                cell.setPlants(plants);

                // Животные (счётчиками по виду)
                for (Species s : Species.values()) {
                    int count = calcRandomInitialCount(s.getMaxPerCell(), config.getInitialAnimalsFillFactor());
                    cell.setCount(s, count);
                }
            }
        }
    }

    private int calcStartUpperBound(int maxPerCell, double fillFactor) {
        if (fillFactor <= 0.0 || maxPerCell <= 0) {
            return 0;
        }

        double raw = maxPerCell * fillFactor;      // может быть 0.6, 0.1 и т.д.
        int base = (int) Math.floor(raw);          // 0
        double fraction = raw - base;              // 0.6

        // стохастически добавляем 1 к верхней границе
        if (RandomUtil.nextDouble() < fraction) {
            base++;
        }

        // защита от выхода за max
        if (base > maxPerCell) {
            base = maxPerCell;
        }

        return base;
    }

    private int calcRandomInitialCount(int maxPerCell, double fillFactor) {
        int upper = calcStartUpperBound(maxPerCell, fillFactor);
        return RandomUtil.nextInt(0, upper);
    }

}
