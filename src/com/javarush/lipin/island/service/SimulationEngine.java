package com.javarush.lipin.island.service;

import com.javarush.lipin.island.config.SimulationConfig;
import com.javarush.lipin.island.model.Island;
import com.javarush.lipin.island.model.Location;
import com.javarush.lipin.island.model.organisms.animal.Animal;
import com.javarush.lipin.island.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {

    private final Island island;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final ExecutorService locationPool = Executors.newFixedThreadPool(SimulationConfig.LOCATION_WORKERS);

    private final LocationProcessor locationProcessor = new LocationProcessor();
    private final StatisticsPrinter statisticsPrinter = new StatisticsPrinter();

    private int tick;

    public SimulationEngine(Island island) {
        this.island = island;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                runTick();
            }
        }, 0, SimulationConfig.TICK_DURATION_MS, TimeUnit.MILLISECONDS);
    }

    private void runTick() {
        tick++;

        // 1) Рост растений
        growPlants();

        // 2) Жизненный цикл животных в параллельных задачах по локациям
        Queue<Migration> migrations = new ConcurrentLinkedQueue<>();
        runAnimals(migrations);

        // 3) Применяем перемещения после обработки всех локаций
        applyMigrations(migrations);

        // 4) Статистика
        statisticsPrinter.print(island, tick);

        // Условие остановки, чтобы симуляция не работала бесконечно
        if (SimulationConfig.MAX_TICKS > 0 && tick >= SimulationConfig.MAX_TICKS) {
            shutdown();
        }
    }

    private void growPlants() {
        for (Location location : island.getAllLocations()) {
            int delta = RandomUtil.nextInt(SimulationConfig.PLANT_GROWTH_MIN, SimulationConfig.PLANT_GROWTH_MAX_EXCLUSIVE);
            location.addPlants(delta);
        }
    }

    private void runAnimals(final Queue<Migration> migrations) {
        List<Callable<Void>> tasks = new ArrayList<>();
        for (final Location location : island.getAllLocations()) {
            tasks.add(new Callable<Void>() {
                @Override
                public Void call() {
                    try {
                        locationProcessor.process(location, island, migrations);
                    } catch (Exception e) {
                        // Просто не даём ошибке упасть и остановить пул
                        e.printStackTrace();
                    }
                    return null;
                }
            });
        }

        try {
            locationPool.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void applyMigrations(Queue<Migration> migrations) {
        Migration migration;
        while ((migration = migrations.poll()) != null) {
            Animal animal = migration.getAnimal();
            if (animal == null || !animal.isAlive()) {
                continue;
            }

            Location from = island.getLocation(migration.getFrom());
            Location to = island.getLocation(migration.getTo());

            // Если животное уже съели/удалили - remove вернёт false
            if (!from.removeAnimal(animal)) {
                continue;
            }

            // Если в новой клетке нет места - возвращаем обратно
            if (!to.addAnimal(animal)) {
                from.addAnimal(animal);
            }
        }
    }

    private void shutdown() {
        scheduler.shutdown();
        locationPool.shutdown();
        System.out.println("\nSimulation finished.\n");
    }
}
