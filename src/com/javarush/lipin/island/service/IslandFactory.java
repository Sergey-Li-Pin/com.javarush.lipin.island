package com.javarush.lipin.island.service;

import com.javarush.lipin.island.config.SimulationConfig;
import com.javarush.lipin.island.config.Species;
import com.javarush.lipin.island.factory.OrganismFactory;
import com.javarush.lipin.island.model.Island;
import com.javarush.lipin.island.model.Location;
import com.javarush.lipin.island.model.organisms.animal.Animal;
import com.javarush.lipin.island.util.RandomUtil;

import java.util.List;

public class IslandFactory {

    private final OrganismFactory organismFactory = OrganismFactory.getInstance();

    public Island createIsland() {
        Island island = new Island(SimulationConfig.ISLAND_WIDTH, SimulationConfig.ISLAND_HEIGHT);

        for (Location location : island.getAllLocations()) {
            initPlants(location);
            initAnimals(location);
        }

        ensureAtLeastOneAnimalOfEachSpecies(island);

        return island;
    }

    private void initPlants(Location location) {
        int max = Species.PLANT.getMaxPerCell();
        int upper = Math.max(1, (int) Math.round(max * SimulationConfig.INITIAL_PLANTS_FILL));
        int initial = RandomUtil.nextInt(0, upper + 1);
        location.setPlantCount(initial);
    }

    private void initAnimals(Location location) {
        for (Species species : Species.animals()) {
            int max = species.getMaxPerCell();
            int upper = Math.max(1, (int) Math.round(max * SimulationConfig.INITIAL_ANIMALS_FILL));
            int count = RandomUtil.nextInt(0, upper + 1);

            for (int i = 0; i < count; i++) {
                Animal animal = organismFactory.createAnimal(species);
                if (!location.addAnimal(animal)) {
                    break;
                }
            }
        }
    }

    private void ensureAtLeastOneAnimalOfEachSpecies(Island island) {
        List<Location> locations = island.getAllLocations();
        for (Species species : Species.animals()) {
            Location randomLocation = RandomUtil.randomElement(locations);
            if (randomLocation == null) {
                return;
            }

            if (countAnimalsOnIsland(island, species) == 0) {
                randomLocation.addAnimal(organismFactory.createAnimal(species));
            }
        }

        if (countPlantsOnIsland(island) == 0) {
            Location randomLocation = RandomUtil.randomElement(locations);
            if (randomLocation != null) {
                randomLocation.setPlantCount(1);
            }
        }
    }

    private int countAnimalsOnIsland(Island island, Species species) {
        int total = 0;
        for (Location location : island.getAllLocations()) {
            total += location.getAnimalsCount(species);
        }
        return total;
    }

    private int countPlantsOnIsland(Island island) {
        int total = 0;
        for (Location location : island.getAllLocations()) {
            total += location.getPlantCount();
        }
        return total;
    }
}
