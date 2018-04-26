package dev.game;

import java.awt.*;

public class SpaceGround {
    Image spaceLayer;

    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int y;
//    private Object[] locations= new Object[100];
//    private int[] stars = new int[100];
    private int starsPosx;
    private int starsPosy;

    public SpaceGround(String path, int x, int y) {
        this.spaceLayer = Toolkit.getDefaultToolkit().getImage(path);
        this.x = x;
        this.y = y;
    }
    public void tick(){
        x+=1;
    }
    public void getStartPos(){
        this.starsPosx= (int )(Math.random() * 999 + 1);
        this.starsPosy= (int )(Math.random() * 999+ 1);
    }
    public void render(Graphics g){
        g.drawImage(spaceLayer,x,y,1000,600,null);
//        g.setColor(Color.WHITE);
//        getStartPos();
//        g.fillOval(starsPosx,starsPosy,7,7);

    }

}
