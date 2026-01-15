package com.javarush.lipin.island.util;

public class Text {

    private Text() {    };

    public static String fmt(String pattern, int a, int b) {
        return pattern.replaceFirst("%d", String.valueOf(a))
                      .replaceFirst("%d", String.valueOf(b));
    }
}
