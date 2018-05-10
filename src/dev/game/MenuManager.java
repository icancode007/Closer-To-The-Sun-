package dev.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MenuManager {
    private Handler handler;
    private ArrayList<MenuObject> obj;

    public MenuManager(Handler handler) {
        this.handler = handler;
        this.obj = new ArrayList<>();
    }

    public void tick(){

        for (MenuObject o :obj) o.tick();
    }

    public void render(Graphics g){
        for (MenuObject o :obj) o.render(g);
    }

    public void onMouseMove(MouseEvent e){
        for (MenuObject o :obj) o.onMouseMove(e);
    }

    public void onMouseReleased(MouseEvent e){
        for (MenuObject o :obj) o.onMouseReleased(e);
    }

    public void addObj(MenuObject o){
        obj.add(o);
    }
    public void removeObj(MenuObject o){
        obj.remove(o);
    }
}
