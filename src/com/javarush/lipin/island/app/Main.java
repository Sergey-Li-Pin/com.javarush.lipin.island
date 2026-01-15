package com.javarush.lipin.island.app;

import com.javarush.lipin.island.config.SimulationConfig;
import com.javarush.lipin.island.entity.island.Island;
import com.javarush.lipin.island.service.*;
import com.javarush.lipin.island.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        // Версия 0.1: маленький остров 10x10, без жизненного цикла животных, только растения + статистика
        SimulationConfig config = new SimulationConfig(
                10, 10,
                1000,      // 1 сек = 1 такт
                0.02,      // 2% от maxPerCell на старте (иначе мыши/гусеницы дадут огромные числа)
                0.50,      // 50% растений на старте
                200,       // max растений на клетке из таблицы
                5,         // +5 растений в такт
                0          // maxTicksSafety: 0 = не ограничивать
        );

        Island island = new Island(config.getWidth(), config.getHeight());

        IslandInitializer initializer = new IslandInitializer();
        initializer.populate(island, config);

        PlantService plantService = new PlantService();
        StatisticsService statisticsService = new StatisticsService();
        ConsoleView view = new ConsoleView();

        SimulationEngine engine = new SimulationEngine(island, config, plantService, statisticsService, view);
        engine.start();

    }
}
