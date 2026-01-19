package com.javarush.lipin.island.config;

public final class SimulationConfig {

    private SimulationConfig() {
    }

    // Размер острова (X * Y)
    public static final int ISLAND_WIDTH = 20;
    public static final int ISLAND_HEIGHT = 10;

    // Длительность такта
    public static final long TICK_DURATION_MS = 1000;

    // Сколько тиков выполнить и остановиться
    public static final int MAX_TICKS = 50;

    // Стартовая заполненность от максимума на клетке
    public static final double INITIAL_PLANTS_FILL = 0.60; // 60% от MAX растений
    public static final double INITIAL_ANIMALS_FILL = 0.05; // 5% от MAX животных

    // Рост растений за тик на каждой клетке
    public static final int PLANT_GROWTH_MIN = 1;
    public static final int PLANT_GROWTH_MAX_EXCLUSIVE = 6; // 1..5

    // Шансы действий
    public static final int MOVE_CHANCE_PERCENT = 50;
    public static final int REPRODUCTION_CHANCE_PERCENT = 25;

    // Cколько «сытости» теряется за тик
    public static final double HUNGER_RATE = 0.25;

    // Сколько потоков использовать для обработки локаций
    public static final int LOCATION_WORKERS = Math.max(2, Runtime.getRuntime().availableProcessors());
}
