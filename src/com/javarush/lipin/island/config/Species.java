package com.javarush.lipin.island.config;

public enum Species {

    // Хищники
    WOLF("Волк", "🐺", 50, 30, 3, 8, true, true),
    BOA("Удав", "🐍", 15, 30, 1, 3, true, true),
    FOX("Лиса", "🦊", 8, 30, 2, 2, true, true),
    BEAR("Медведь", "🐻", 500, 5, 2, 80, true, true),
    EAGLE("Орел", "🦅", 6, 20, 3, 1, true, true),

    // Травоядные (утка и кабан могут есть гусениц/мышей)
    HORSE("Лошадь", "🐎", 400, 20, 4, 60, true, false),
    DEER("Олень", "🦌", 300, 20, 4, 50, true, false),
    RABBIT("Кролик", "🐇", 2, 150, 2, 0.45, true, false),
    MOUSE("Мышь", "🐁", 0.05, 500, 1, 0.01, true, false),
    GOAT("Коза", "🐐", 60, 140, 3, 10, true, false),
    SHEEP("Овца", "🐑", 70, 140, 3, 15, true, false),
    BOAR("Кабан", "🐗", 400, 50, 2, 50, true, false),
    BUFFALO("Буйвол", "🐃", 700, 10, 3, 100, true, false),
    DUCK("Утка", "🦆", 1, 200, 4, 0.15, true, false),
    CATERPILLAR("Гусеница", "🐛", 0.01, 1000, 0, 0, true, false),

    // Растения
    PLANT("Растения", "🌿", 1, 200, 0, 0, false, false);

    private final String title;
    private final String icon;
    private final double weight;
    private final int maxPerCell;
    private final int speed;
    private final double foodNeeded;
    private final boolean animal;
    private final boolean predator;

    Species(String title,
            String icon,
            double weight,
            int maxPerCell,
            int speed,
            double foodNeeded,
            boolean animal,
            boolean predator) {
        this.title = title;
        this.icon = icon;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
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

    public boolean isAnimal() {
        return animal;
    }

    public boolean isPredator() {
        return predator;
    }

    public boolean isPlant() {
        return this == PLANT;
    }

    public static Species[] animals() {
        return new Species[]{
                WOLF, BOA, FOX, BEAR, EAGLE,
                HORSE, DEER, RABBIT, MOUSE, GOAT, SHEEP, BOAR, BUFFALO, DUCK, CATERPILLAR
        };
    }
}
