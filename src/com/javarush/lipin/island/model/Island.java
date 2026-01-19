package com.javarush.lipin.island.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Island {

    private final int width;
    private final int height;
    private final Location[][] locations;
    private final List<Location> allLocations;

    public Island(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Island size must be positive");
        }
        this.width = width;
        this.height = height;
        this.locations = new Location[height][width];

        List<Location> tmp = new ArrayList<>(width * height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Location location = new Location(new Position(x, y));
                locations[y][x] = location;
                tmp.add(location);
            }
        }
        this.allLocations = Collections.unmodifiableList(tmp);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Location getLocation(int x, int y) {
        return locations[y][x];
    }

    public Location getLocation(Position position) {
        return getLocation(position.getX(), position.getY());
    }

    public List<Location> getAllLocations() {
        return allLocations;
    }
}
