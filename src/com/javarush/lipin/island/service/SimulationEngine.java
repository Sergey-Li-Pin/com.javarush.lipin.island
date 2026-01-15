package com.javarush.lipin.island.service;

import com.javarush.lipin.island.config.SimulationConfig;
import com.javarush.lipin.island.entity.island.Island;
import com.javarush.lipin.island.service.IslandStats;
import com.javarush.lipin.island.view.ConsoleView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {

    private final Island island;
    private final SimulationConfig config;

    private final PlantService plantService;
    private final StatisticsService statisticsService;
    private final ConsoleView view;

    private ScheduledExecutorService scheduler;
    private int tick = 0;

    public SimulationEngine(Island island,
                            SimulationConfig config,
                            PlantService plantService,
                            StatisticsService statisticsService,
                            ConsoleView view) {
        this.island = island;
        this.config = config;
        this.plantService = plantService;
        this.statisticsService = statisticsService;
        this.view = view;
    }

    public void start() {
        scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                doTick();
            } catch (Exception e) {
                e.printStackTrace();
                stop();
            }
        }, 0, config.getTickMillis(), TimeUnit.MILLISECONDS);
    }

    private void doTick() {
        tick++;

        // рост растений
        plantService.grow(island, config);

        // статистика по всему острову
        IslandStats stats = statisticsService.calculate(island, tick);
        view.print(stats);

        // стоп-условия
        if (stats.getAnimalsTotalAll() == 0) {
            System.out.println("Все животные умерли. Симуляция завершена.");
            stop();
            return;
        }

        if (config.getMaxTicksSafety() > 0 && tick >= config.getMaxTicksSafety()) {
            System.out.println("Достигнут лимит maxTicksSafety=" + config.getMaxTicksSafety() + ". Остановка (режим разработки).");
            stop();
        }
    }

    public void stop() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
}
