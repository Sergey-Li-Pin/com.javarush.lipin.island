package com.javarush.lipin.island.entity.island;

import com.javarush.lipin.island.entity.location.Location;

public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                locations[y][x] = new Location();
            }
        }
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Location getLocation(int x, int y) {
        return locations[y][x];
    }

    public Location[][] getLocations() {
        return locations;
    }
}
