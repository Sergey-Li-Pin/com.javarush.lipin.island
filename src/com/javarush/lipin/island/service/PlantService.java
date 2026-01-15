package com.javarush.lipin.island.service;

import com.javarush.lipin.island.config.SimulationConfig;
import com.javarush.lipin.island.entity.island.Island;
import com.javarush.lipin.island.entity.location.Location;

public class PlantService {

    public void grow(Island island, SimulationConfig config) {
        int growth = config.getPlantsGrowthPerTick();
        int max = config.getPlantsMaxPerCell();

        for (Location[] row : island.getLocations()) {
            for (Location cell : row) {
                cell.addPlants(growth, max);
            }
        }
    }
}
