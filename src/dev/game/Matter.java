package dev.game;

import java.awt.*;

public abstract class Matter extends Entity {

    protected  int energy;

    public Matter(float x, float y) {
        super(x, y);
        energy = 100;
    }

}
