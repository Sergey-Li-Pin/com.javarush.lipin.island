package com.javarush.lipin.island.config;

/**
 * Все виды организмов на острове + их базовые характеристики.
 *
 * Добавлено: reproductionChancePercent — шанс размножения (0..100) для конкретного вида.
 */
public enum Species {

    // Хищники
    WOLF("Волк", "🐺", 50, 30, 3, 8, 4, true, true),
    BOA("Удав", "🐍", 15, 30, 1, 3, 5, true, true),
    FOX("Лиса", "🦊", 8, 30, 2, 2, 6, true, true),
    BEAR("Медведь", "🐻", 500, 5, 2, 80, 2, true, true),
    EAGLE("Орел", "🦅", 6, 20, 3, 1, 4, true, true),

    // Травоядные
    HORSE("Лошадь", "🐎", 400, 20, 4, 60, 2, true, false),
    DEER("Олень", "🦌", 300, 20, 4, 50, 3, true, false),
    RABBIT("Кролик", "🐇", 2, 150, 2, 0.45, 20, true, false),
    MOUSE("Мышь", "🐁", 0.05, 500, 1, 0.01, 25, true, false),
    GOAT("Коза", "🐐", 60, 140, 3, 10, 10, true, false),
    SHEEP("Овца", "🐑", 70, 140, 3, 15, 9, true, false),
    BOAR("Кабан", "🐗", 400, 50, 2, 50, 8, true, false),
    BUFFALO("Буйвол", "🐃", 700, 10, 3, 100, 2, true, false),
    DUCK("Утка", "🦆", 1, 200, 4, 0.15, 12, true, false),
    CATERPILLAR("Гусеница", "🐛", 0.01, 1000, 0, 0, 15, true, false),

    // Растения
    PLANT("Растения", "🌿", 1, 200, 0, 0, 0, false, false);

    private final String title;
    private final String icon;
    private final double weight;
    private final int maxPerCell;
    private final int speed;
    private final double foodNeeded;
    private final int reproductionChancePercent;
    private final boolean animal;
    private final boolean predator;

    Species(String title,
            String icon,
            double weight,
            int maxPerCell,
            int speed,
            double foodNeeded,
            int reproductionChancePercent,
            boolean animal,
            boolean predator) {
        this.title = title;
        this.icon = icon;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;

        if (reproductionChancePercent < 0 || reproductionChancePercent > 100) {
            throw new IllegalArgumentException("reproductionChancePercent must be 0..100 for " + this);
        }
        this.reproductionChancePercent = reproductionChancePercent;

        this.animal = animal;
        this.predator = predator;
    }

    public String getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPerCell() {
        return maxPerCell;
    }

    public int getSpeed() {
        return speed;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    /**
     * Шанс размножения (в процентах) для конкретного вида.
     * Используется в Animal.multiply().
     */
    public int getReproductionChancePercent() {
        return reproductionChancePercent;
    }

    public boolean isAnimal() {
        return animal;
    }

    public boolean isPredator() {
        return predator;
    }

    public boolean isPlant() {
        return this == PLANT;
    }

    /**
     * Список всех животных (без растений).
     */
    public static Species[] animals() {
        return new Species[]{
                WOLF, BOA, FOX, BEAR, EAGLE,
                HORSE, DEER, RABBIT, MOUSE, GOAT, SHEEP, BOAR, BUFFALO, DUCK, CATERPILLAR
        };
    }
}
