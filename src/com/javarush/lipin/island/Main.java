package com.javarush.lipin.island;

import com.javarush.lipin.island.model.Island;
import com.javarush.lipin.island.service.IslandFactory;
import com.javarush.lipin.island.service.SimulationEngine;

public class Main {

    public static void main(String[] args) {
        Island island = new IslandFactory().createIsland();
        SimulationEngine engine = new SimulationEngine(island);
        engine.start();
    }
}
