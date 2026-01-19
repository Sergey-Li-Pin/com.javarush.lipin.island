package com.javarush.lipin.island.model.organisms;

import com.javarush.lipin.island.config.Species;

public interface Eatable {

    Species getSpecies();

    double getWeight();
}
