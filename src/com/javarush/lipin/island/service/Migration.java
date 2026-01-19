package com.javarush.lipin.island.service;

import com.javarush.lipin.island.model.Position;
import com.javarush.lipin.island.model.organisms.animal.Animal;

public class Migration {
    private final Animal animal;
    private final Position from;
    private final Position to;

    public Migration(Animal animal, Position from, Position to) {
        this.animal = animal;
        this.from = from;
        this.to = to;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
