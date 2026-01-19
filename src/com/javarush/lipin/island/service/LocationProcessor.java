package com.javarush.lipin.island.service;

import com.javarush.lipin.island.config.Species;
import com.javarush.lipin.island.model.Island;
import com.javarush.lipin.island.model.Location;
import com.javarush.lipin.island.model.Position;
import com.javarush.lipin.island.model.organisms.animal.Animal;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class LocationProcessor {

    public void process(Location location, Island island, Queue<Migration> migrations) {
        if (location == null || island == null || migrations == null) {
            return;
        }

        Position from = location.getPosition();

        // Для каждого вида животных на клетке
        for (Species species : Species.animals()) {
            List<Animal> animals = location.getAnimals(species);
            if (animals.isEmpty()) {
                continue;
            }

            // 1) Голодание и смерть от голода
            removeStarved(animals);
            if (animals.isEmpty()) {
                continue;
            }

            // 2) Поесть
            for (int i = 0; i < animals.size(); i++) {
                Animal animal = animals.get(i);
                if (animal.isAlive()) {
                    animal.eat(location);
                }
            }

            // 3) Размножение (максимум один детёныш за тик на вид в одной клетке)
            Animal child = animals.get(0).multiply(location);
            if (child != null) {
                location.addAnimal(child);
            }

            // 4) Передвижение
            for (int i = 0; i < animals.size(); i++) {
                Animal animal = animals.get(i);
                if (!animal.isAlive()) {
                    continue;
                }

                Position to = animal.move(island, from);
                if (to != null && !to.equals(from)) {
                    migrations.add(new Migration(animal, from, to));
                }
            }
        }
    }

    private void removeStarved(List<Animal> animals) {
        Iterator<Animal> iterator = animals.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            animal.decreaseSatiety();
            if (!animal.isAlive()) {
                iterator.remove();
            }
        }
    }
}
