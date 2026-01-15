package com.javarush.lipin.island.util;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {
    private RandomUtil() {}

    public static int nextInt(int minInclusive, int maxInclusive) {
        if (maxInclusive < minInclusive) {
            return minInclusive;
        }
        if (maxInclusive == minInclusive) {
            return minInclusive;
        }
        return ThreadLocalRandom.current().nextInt(minInclusive, maxInclusive + 1);
    }

    public static double nextDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }
}
