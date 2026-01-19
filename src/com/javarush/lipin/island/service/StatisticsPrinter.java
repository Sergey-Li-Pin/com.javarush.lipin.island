package com.javarush.lipin.island.service;

import com.javarush.lipin.island.config.Species;
import com.javarush.lipin.island.model.Island;
import com.javarush.lipin.island.model.Location;

public class StatisticsPrinter {

    public void print(Island island, int tick) {
        if (island == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n================ TICK ").append(tick).append(" =================\n");

        int plantTotal = 0;
        for (Location location : island.getAllLocations()) {
            plantTotal += location.getPlantCount();
        }
        sb.append(Species.PLANT.getIcon()).append(" ").append(Species.PLANT.getTitle()).append(": ").append(plantTotal).append("\n");

        for (Species species : Species.animals()) {
            int total = 0;
            for (Location location : island.getAllLocations()) {
                total += location.getAnimalsCount(species);
            }
            sb.append(species.getIcon()).append(" ").append(species.getTitle()).append(": ").append(total).append("\n");
        }

        System.out.println(sb);
    }
}
