package dev.game;

import java.awt.*;

public abstract class Matter extends Entity {

    public static final int DEFAULT_ENERGY = 100;
    public static final float DEFAULT_SPEED = 5.0f;
    public static final int DEFAULT_WIDTH = 64;
    public static final int DEFAULT_HEIGHT = 64;



    protected  int energy;
    protected  float speed;
    protected float xMove,yMove;

    public Matter(float x, float y, int width, int height) {
        super(x, y, width,height);
        energy = DEFAULT_ENERGY;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;

    }
    public void move(){
        x += xMove;
        y += yMove;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
