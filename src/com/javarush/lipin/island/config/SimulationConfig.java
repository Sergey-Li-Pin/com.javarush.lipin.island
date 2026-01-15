package com.javarush.lipin.island.config;

public class SimulationConfig {
    private final int width;
    private final int height;

    private final long tickMillis;

    // Стартовые заполнения (0.0 .. 1.0)
    private final double initialAnimalsFillFactor;
    private final double initialPlantsFillFactor;

    // Растения
    private final int plantsMaxPerCell;
    private final int plantsGrowthPerTick;

    // Стоп-условие для разработки (0 = не ограничивать)
    private final int maxTicksSafety;

    public SimulationConfig(int width,
                            int height,
                            long tickMillis,
                            double initialAnimalsFillFactor,
                            double initialPlantsFillFactor,
                            int plantsMaxPerCell,
                            int plantsGrowthPerTick,
                            int maxTicksSafety) {
        this.width = width;
        this.height = height;
        this.tickMillis = tickMillis;
        this.initialAnimalsFillFactor = initialAnimalsFillFactor;
        this.initialPlantsFillFactor = initialPlantsFillFactor;
        this.plantsMaxPerCell = plantsMaxPerCell;
        this.plantsGrowthPerTick = plantsGrowthPerTick;
        this.maxTicksSafety = maxTicksSafety;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public long getTickMillis() { return tickMillis; }

    public double getInitialAnimalsFillFactor() { return initialAnimalsFillFactor; }
    public double getInitialPlantsFillFactor() { return initialPlantsFillFactor; }

    public int getPlantsMaxPerCell() { return plantsMaxPerCell; }
    public int getPlantsGrowthPerTick() { return plantsGrowthPerTick; }

    public int getMaxTicksSafety() { return maxTicksSafety; }
}
