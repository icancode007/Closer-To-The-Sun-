package dev.game;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State
{
    static int btx,bty, ftx,fty, bfx,bfy, bbx,bby;



    public SpaceGround spaceField,starField,starFieldz;

    public ArrayList<Asteroid> smallAsteroids;

    private SpaceShip redSpaceShip;
    private Enemy blackSpaceShip;

    public static int score = 0;

    public GameState(Handler handler)
    {
        super(handler);
        btx = 990;
        bty = 5;

        ftx = 0;
        fty = 5;

        bfx = 0;
        bfy = 600;

        bbx = 990;
        bby = 600;

        redSpaceShip = new SpaceShip(handler,800,20);
        blackSpaceShip = new Enemy(handler, -3500,20,redSpaceShip);

        spaceField = new SpaceGround("./res/Artwork/farback.gif",0,0,handler);
        starFieldz = new SpaceGround("./res/Artwork/starfield.png",-1000,0,handler);
        starField  = new SpaceGround("./res/Artwork/starfield.png",0,0,handler);

        smallAsteroids = new ArrayList<>();
        for (int i = 0; i < 80 ; i++)
        {
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
            starField.setX(-2000);
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
        blackSpaceShip.tick();

    }

    @Override
    public void render(Graphics g)
    {
        spaceField.render(g);
        g.setColor(Color.GREEN);
        g.drawLine(ftx,fty,btx,bty);
        g.drawLine(btx,bty,bbx,bby);
        g.drawLine(bfx,bfy,bbx,bby);

        starField.render(g);

        redSpaceShip.render(g);

        if(starField.getX() > 300)
        {
            starFieldz.render(g);
        }

            for (Asteroid i:smallAsteroids)
            {
                if((i.collide(redSpaceShip.shipFrame)||i.collide(redSpaceShip.shipGunFrame))){
                    i.pushBack();
                    i.hit =true;
                    if(redSpaceShip.energy > 100)
                        redSpaceShip.energy--;
                    else
                        redSpaceShip.depletedEnergy = true;
                }
                i.render(g);

                for (int j = 0; j < redSpaceShip.drawnlaser.size() ; j++)
                {
                    if (i.collide(redSpaceShip.drawnlaser.get(j).laserFrame))
                    {
                        redSpaceShip.drawnlaser.get(j).setProjectileY(-15);
                        i.pushBack();
                        i.hit =true;
                    }

                }
            }

            blackSpaceShip.render(g);
    }
}
