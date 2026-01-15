package com.javarush.lipin.island.entity.animals;

public enum Species {

    // Predators
    WOLF("🐺", true, 50.0, 30, 3, 8.0),
    BOA("🐍", true, 15.0, 30, 1, 3.0),
    FOX("🦊", true, 8.0, 30, 2, 2.0),
    BEAR("🐻", true, 500.0, 5, 2, 80.0),
    EAGLE("🦅", true, 6.0, 20, 3, 1.0),

    // Herbivores
    HORSE("🐎", false, 400.0, 20, 4, 60.0),
    DEER("🦌", false, 300.0, 20, 4, 50.0),
    RABBIT("🐇", false, 2.0, 150, 2, 0.45),
    MOUSE("🐁", false, 0.05, 500, 1, 0.01),
    GOAT("🐐", false, 60.0, 140, 3, 10.0),
    SHEEP("🐑", false, 70.0, 140, 3, 15.0),
    BOAR("🐗", false, 400.0, 50, 2, 50.0),
    BUFFALO("🐃", false, 700.0, 10, 3, 100.0),
    DUCK("🦆", false, 1.0, 200, 4, 0.15),
    CATERPILLAR("🐛", false, 0.01, 1000, 0, 0.0);

    private final String emoji;
    private final boolean predator;
    private final double weightKg;
    private final int maxPerCell;
    private final int speed;
    private final double foodNeededKg;

    Species(String emoji,
            boolean predator,
            double weightKg,
            int maxPerCell,
            int speed,
            double foodNeededKg) {
        this.emoji = emoji;
        this.predator = predator;
        this.weightKg = weightKg;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.foodNeededKg = foodNeededKg;
    }

    public String getEmoji() { return emoji; }
    public boolean isPredator() { return predator; }

    public double getWeightKg() { return weightKg; }
    public int getMaxPerCell() { return maxPerCell; }
    public int getSpeed() { return speed; }
    public double getFoodNeededKg() { return foodNeededKg; }
}
