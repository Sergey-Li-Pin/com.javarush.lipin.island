package com.javarush.lipin.island.model.organisms.animal;

import com.javarush.lipin.island.config.EatingProbability;
import com.javarush.lipin.island.config.SimulationConfig;
import com.javarush.lipin.island.config.Species;
import com.javarush.lipin.island.factory.OrganismFactory;
import com.javarush.lipin.island.model.Island;
import com.javarush.lipin.island.model.Location;
import com.javarush.lipin.island.model.Position;
import com.javarush.lipin.island.model.organisms.Eatable;
import com.javarush.lipin.island.model.organisms.Organism;
import com.javarush.lipin.island.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Базовый класс для всех животных.
 */
public abstract class Animal extends Organism {

    private double satiety;
    private boolean alive = true;

    protected Animal(Species species) {
        super(species);
        initSatiety();
    }

    private void initSatiety() {
        double need = getSpecies().getFoodNeeded();
        if (need <= 0) {
            satiety = 0;
            return;
        }
        satiety = need * RandomUtil.nextDouble(0.50, 1.01); // 50-100% от нормы
        if (satiety > need) {
            satiety = need;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
    }

    /**
     * Голодание: каждый тик животное теряет часть «сытости».
     */
    public void decreaseSatiety() {
        if (!alive) {
            return;
        }
        double need = getSpecies().getFoodNeeded();
        if (need <= 0) {
            return;
        }
        satiety -= need * SimulationConfig.HUNGER_RATE;
        if (satiety <= 0) {
            satiety = 0;
            die();
        }
    }

    /**
     * Животное пытается поесть на своей клетке.
     */
    public void eat(Location location) {
        if (!alive || location == null) {
            return;
        }

        double need = getSpecies().getFoodNeeded();
        if (need <= 0) {
            return;
        }
        if (satiety >= need) {
            return; // уже сыто
        }

        List<Species> possibleFoods = collectPossibleFoods(location);
        if (possibleFoods.isEmpty()) {
            return;
        }

        Species chosenFood = RandomUtil.randomElement(possibleFoods);
        if (chosenFood == null) {
            return;
        }

        if (chosenFood.isPlant()) {
            eatPlants(location);
            return;
        }

        int chance = EatingProbability.getChance(getSpecies(), chosenFood);
        if (!RandomUtil.chance(chance)) {
            return;
        }

        List<Animal> victims = location.getAnimals(chosenFood);
        Animal victim = RandomUtil.randomElement(victims);
        if (victim == null) {
            return;
        }

        victim.die();
        location.removeAnimal(victim);
        eat(victim);
    }

    /**
     * Выбор направления (и шага) передвижения.
     *
     * @return новая позиция или та же самая, если животное решило не двигаться.
     */
    public Position move(Island island, Position from) {
        if (!alive || island == null || from == null) {
            return from;
        }

        int speed = getSpecies().getSpeed();
        if (speed <= 0) {
            return from;
        }

        if (!RandomUtil.chance(SimulationConfig.MOVE_CHANCE_PERCENT)) {
            return from;
        }

        int step = RandomUtil.nextInt(1, speed + 1);
        int direction = RandomUtil.nextInt(0, 4);

        int newX = from.getX();
        int newY = from.getY();

        switch (direction) {
            case 0:
                newY -= step; // вверх
                break;
            case 1:
                newY += step; // вниз
                break;
            case 2:
                newX -= step; // влево
                break;
            case 3:
                newX += step; // вправо
                break;
            default:
                // nothing
                break;
        }

        // Ограничиваем границами острова
        newX = Math.max(0, Math.min(island.getWidth() - 1, newX));
        newY = Math.max(0, Math.min(island.getHeight() - 1, newY));

        return new Position(newX, newY);
    }

    /**
     * Размножение: если на клетке есть пара (минимум 2 особи вида) -
     * с шансом, который зависит от вида (Species.getReproductionChancePercent()),
     * появляется детёныш.
     */
    public Animal multiply(Location location) {
        if (!alive || location == null) {
            return null;
        }

        int count = location.getAnimalsCount(getSpecies());
        if (count < 2) {
            return null;
        }

        if (count >= getSpecies().getMaxPerCell()) {
            return null;
        }

        int chance = getSpecies().getReproductionChancePercent();
        if (!RandomUtil.chance(chance)) {
            return null;
        }

        return OrganismFactory.getInstance().createAnimal(getSpecies());
    }

    // ---- Внутренние методы ----

    protected void eat(Eatable food) {
        if (!alive || food == null) {
            return;
        }

        double need = getSpecies().getFoodNeeded();
        if (need <= 0) {
            return;
        }

        satiety = Math.min(need, satiety + food.getWeight());
    }

    private void eatPlants(Location location) {
        double need = getSpecies().getFoodNeeded();
        if (need <= 0) {
            return;
        }
        double missing = need - satiety;
        if (missing <= 0) {
            return;
        }

        double plantWeight = Species.PLANT.getWeight();
        int plantsToEat = (int) Math.ceil(missing / plantWeight);
        int removed = location.removePlants(plantsToEat);

        satiety = Math.min(need, satiety + removed * plantWeight);
    }

    private List<Species> collectPossibleFoods(Location location) {
        List<Species> foods = new ArrayList<>();
        for (Species food : Species.values()) {
            int chance = EatingProbability.getChance(getSpecies(), food);
            if (chance <= 0) {
                continue;
            }

            if (food.isPlant()) {
                if (location.getPlantCount() > 0) {
                    foods.add(food);
                }
                continue;
            }

            if (food.isAnimal()) {
                if (location.getAnimalsCount(food) > 0) {
                    foods.add(food);
                }
            }
        }
        return foods;
    }
}
