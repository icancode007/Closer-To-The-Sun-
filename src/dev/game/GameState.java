package dev.game;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State
{

    private  SpaceShip redSpaceShip;

    public   SpaceGround spaceField;
    private  SpaceGround starField;
    private  SpaceGround starFieldz;

    private ArrayList<Asteroid> smallAsteroids;

    public GameState(Handler handler)
    {
        super(handler);
        redSpaceShip = new SpaceShip(handler,800,20);
        spaceField = new SpaceGround("./res/Artwork/farback.gif",0,0,handler);
        starField = new SpaceGround("./res/Artwork/starfield.png",0,0,handler);
        starFieldz = new SpaceGround("./res/Artwork/starfield.png",-1000,0,handler);
        smallAsteroids = new ArrayList<>();
        for (int i = 0; i < 20 ; i++)
        {
//            smallAsteroids.add(new Asteroid(("./res/Artwork/asteroids/aster1.png"),Asteroid.RanPosX(),Asteroid.RanPosY(),64,64));
            smallAsteroids.add(new Asteroid(handler,Asteroid.RanPosX(),Asteroid.RanPosY(),64,64));
        }
    }

    public SpaceGround getSpaceField() {
        return spaceField;
    }

    @Override
    public void tick()
    {
        redSpaceShip.tick();
        starField.tick();
        if(starField.getX() > 1000)
        {
            starField.setX(-1000);
        }

            starFieldz.tick();
        if (starFieldz.getX()>1000)
        {
            starFieldz.setX(-1000);
        }
        for (Asteroid i :smallAsteroids)
        {
            i.tick();
        }

    }

    @Override
    public void render(Graphics g)
    {
        spaceField.render(g);
        starField.render(g);
        redSpaceShip.render(g);
        if(starField.getX() > 300)
        {
            starFieldz.render(g);
        }

        for (Asteroid i:smallAsteroids)
        {
            i.render(g);
            for (int j = 0; j < redSpaceShip.drawnlaser.size() ; j++)
            {
                if (i.colliding(redSpaceShip.drawnlaser.get(j).laserFrame))
                {
                    i.pushBack();
                    i.hit =true;
                }

            }

        }

    }
}
