package dev.game;

import java.awt.*;

public class Rectangle {
    double posX,posY,dx,dy;
    int h,w;

    public Rectangle(double posX, double posY, int h, int w) {
        this.posX = posX;
        this.posY = posY;
        this.h = h;
        this.w = w;
    }
    public void drawRect(Graphics g, double x, double y){
        this.posX = x;
        this.posY = y;
        g.drawRect((int)posX,(int)posY,this.w,this.h);
    }

    public void rectSpeed(int x, int y){
        this.dx = x;
        this.dy = y;
    }
    public void move(){
        this.posX += this.dx;
        this.posY += this.dy;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}
