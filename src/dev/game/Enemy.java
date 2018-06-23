package dev.game;

import java.awt.*;
import java.util.ArrayList;

import static dev.game.GameState.score;

public class Enemy extends Matter
{
    public   Rectangle shipFrame,shipGunFrame;
    public  SpaceShip heroSpaceship;
    private Image enemyShip;
    private Animation [] boom,flames;
    public boolean depletedEnergy, stopDRAWING;
    public ArrayList<SpaceShip.Projectiles> drawnlaser= new ArrayList<>();

    public int munitionCount=-1;

    public Enemy(Handler handler, float x, float y, SpaceShip heroSpaceship)
    {
        super(handler, x, y,DEFAULT_WIDTH, DEFAULT_HEIGHT);

        flames = new Animation[6];
        boom = new Animation[9];

        enemyShip = Toolkit.getDefaultToolkit().getImage("./res/Artwork/enemyship.png");

        this.setEnergy(1000);

        this.depletedEnergy = false;
        this.stopDRAWING = false;
        this.heroSpaceship = heroSpaceship;

        for(int i = 0; i < 6;i++)
        {
            flames[i]=new Animation("./res/Artwork/badFlame/redflame",5,10);
        }
        for(int i = 0; i< 9;i++)
        {
            boom[i]=new Animation("./res/Artwork/badSexp/shipexp",8,10);
        }

    }

    @Override
    public void tick()
    {
        if(this.x < 40)
            this.x+=5;
        this.y = heroSpaceship.getY();
    }

    @Override
    public void render(Graphics g)
    {

        if(this.depletedEnergy && !stopDRAWING)
        {
            //            erasecCollition(this);
            g.drawImage(boom[0].nextImage(),(int)x,(int)y,null);

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run()
                        {
                            stopDRAWING=true;
                            //call NextLVL

                        }
                    },
                    900
            );

        }

        if(!this.depletedEnergy && !stopDRAWING)
        {
            g.drawImage(enemyShip,(int)x,(int)y,null);
            g.drawImage(flames[0].nextImage(), (int)x-40, (int)y+58, null);
        }

    }
}
