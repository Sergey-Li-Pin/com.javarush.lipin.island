package com.javarush.lipin.island.util;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {

    private RandomUtil() {
    }

    public static int nextInt(int originInclusive, int boundExclusive) {
        return ThreadLocalRandom.current().nextInt(originInclusive, boundExclusive);
    }

    public static double nextDouble(double originInclusive, double boundExclusive) {
        return ThreadLocalRandom.current().nextDouble(originInclusive, boundExclusive);
    }

    public static boolean chance(int percent) {
        if (percent <= 0) {
            return false;
        }
        if (percent >= 100) {
            return true;
        }
        return ThreadLocalRandom.current().nextInt(100) < percent;
    }

    public static <T> T randomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }
}
