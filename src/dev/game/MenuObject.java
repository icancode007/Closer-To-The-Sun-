package dev.game;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class MenuObject {

    java.awt.Rectangle layer;

    double x,y;

    int w,h;

    public boolean hovering=false;

    public MenuObject( double x, double y,int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        layer = new java.awt.Rectangle((int)x,(int)y,w,h);
    }
    public abstract void render(Graphics g);
    public abstract void tick();
    public abstract void onClick();

    public void onMouseMove(MouseEvent e){
        if(this.layer.contains(e.getX(),e.getY())) {
            this.hovering = true;
        }
        else
            this.hovering = false;
    }
    public void onMouseReleased(MouseEvent e){
        if (hovering) onClick();
    }

}
