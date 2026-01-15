package com.javarush.lipin.island.view;

import com.javarush.lipin.island.entity.animals.Species;
import com.javarush.lipin.island.service.IslandStats;

public class ConsoleView {

    public void print(IslandStats stats) {
        System.out.println();
        System.out.println("=== TICK " + stats.getTick() + " ===");
        System.out.println("🌿 = " + stats.getPlantsTotal());
        System.out.println("Всего животных: " + stats.getAnimalsTotalAll());

        int i = 0;
        StringBuilder line = new StringBuilder();
        for (Species s : Species.values()) {
            line.append(s.getEmoji()).append(" = ").append(stats.getAnimalsTotal(s)).append("   ");
            i++;
            if (i % 5 == 0) {
                System.out.println(line);
                line.setLength(0);
            }
        }
        if (line.length() > 0) {
            System.out.println(line);
        }
    }
}
