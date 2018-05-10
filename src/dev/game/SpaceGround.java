package dev.game;

import java.awt.*;

public class SpaceGround
{
    private Handler handler;
    Image spaceLayer;
    private int x;

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x += x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {

        this.y = y;
    }

    private int y;


    public SpaceGround(String path, int x, int y,Handler handler) {

        this.spaceLayer = Toolkit.getDefaultToolkit().getImage(path);
        this.x = x;
        this.y = y;
    }
    public void tick()
    {
        x+=1;
    }
    public void render(Graphics g)
    {
        g.drawImage(spaceLayer,x,y,1000,600,null);
    }
    public void getInput(){
        if(handler.getKeyManager().left)
        {
            x+=1;
        }
    }
}
