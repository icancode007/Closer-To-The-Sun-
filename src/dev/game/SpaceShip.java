package dev.game;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

public class SpaceShip extends Matter
{
    public   Rectangle shipFrame,shipGunFrame;
    private Image spaceShip = Toolkit.getDefaultToolkit().getImage("./res/Artwork/ship.png");
    private Animation[] flames, smFlames;

    boolean shooting = false;

    public ArrayList<Projectiles> drawnlaser= new ArrayList<>();
    private int munitionCount=-1;

    public void setMunitionCount(int munitionCount)
    {
        this.munitionCount = munitionCount;
    }

    public SpaceShip(Handler handler, float x, float y)
    {
        super(handler, x, y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        flames = new Animation[6];
        smFlames = new Animation[6];
        shipFrame = new Rectangle(x,y,120,80);
        shipGunFrame= new Rectangle(x,y,50,50);;
        loadProjectiles();

        for(int i = 0; i<6;i++)
        {
            flames[i]=new Animation("./res/Artwork/flames/redflame",5,10);
            smFlames[i]=new Animation("./res/Artwork/flames/littlef",5,10);
        }
    }
    public void loadProjectiles()
    {

    }

    @Override
    public void tick()
    {
        getInput();
        move();

    }

    public void getInput()
    {
        xMove = 0;
        yMove = 0;
        if(handler.getKeyManager().up)
        {
            yMove = -speed;
        }
        if(handler.getKeyManager().down)
        {
            yMove = speed;
        }
        if(handler.getKeyManager().left)
        {
            xMove = -speed;
        }
        if(handler.getKeyManager().right)
        {
            xMove = +speed;
        }
        if(handler.getKeyManager().spaceBar)
        {
            shoot();
        }
    }



    @Override
    public void render(Graphics g)
    {
        /* If there was the need to resize my spaceship I can always pass 2 more values in the method below */
        g.drawImage(spaceShip,(int)x,(int)y,null);
//        g.drawImage(flames[0].nextImage(),   (int)x + 120, (int)y + 3, null);
//        g.drawImage(flames[0].nextImage(),   (int)x + 120, (int)y + 89`, null);
        g.drawImage(smFlames[0].nextImage(), (int)x + 129, (int)y + 40, null);
        g.drawImage(smFlames[0].nextImage(), (int)x + 129, (int)y + 69, null);

        /*
            g.setColor(Color.WHITE);
            shipFrame.drawRect(g,x+60,y);//Just here for Collition detection
            shipGunFrame.drawRect(g,x+5,y+40);//Just here for Collition detection
        */


//        if((lasers[munitionCount].getProjectileX()> 1 && lasers[munitionCount].getProjectileX()< 1000))
//        {
//                    lasers[munitionCount].drawProjectile(g);
//        }
        if(drawnlaser.size()!= 0)
        {
            for(Projectiles l : drawnlaser){
                if(l != null )
                {
                    l.drawProjectile(g);
                }
                else
                {
                    drawnlaser.get(munitionCount).drawProjectile(g);
                    shooting = false;
                }
            }
        }
    }

    public void shoot()
    {
        shooting=true;
        if(munitionCount < 999 )
        {
            munitionCount++;
            drawnlaser.add(new Projectiles());
        }



    }

    class Projectiles
    {

        private Image projectile= Toolkit.getDefaultToolkit().getImage("./res/Artwork/redLaser.png");

        public double projectileX;
        public double projectileY;
        public  boolean hit;
        public Rectangle laserFrame;
        private Random rnd = new Random();

        private int r = rnd.nextInt() % 7;

        public double getProjectileX() {
            return projectileX;
        }

        public Projectiles()
        {
            this.projectileX = getX()-30;
            this.projectileY = getY()+53;
            laserFrame = new Rectangle(this.projectileX,this.projectileY,15,53);
            this.hit = false;
        }
        public boolean gotHit(){
            return this.hit = true;
        }
        public Projectiles(Graphics g)
        {
            this.projectileX = getX()-30;
            this.projectileY = getY()+53;
            laserFrame = new Rectangle(this.projectileX,this.projectileY,15,53);
            this.drawProjectile(g);
        }
        public void setImage(String newPath)
        {
            projectile=Toolkit.getDefaultToolkit().getImage(newPath);
        }

        public boolean colliding(Rectangle o )
        {
            return  (x  < o.posX +    o.w) &&
                    (x  + width >  o.posX) &&
                    (y  < o.posY +   o.h ) &&
                    (y + width > o.posY);

        }

        public void drawProjectile(Graphics g)
        {
            g.drawImage(projectile,(int)projectileX,(int)projectileY,null);
            this.translateProjectile();
            laserFrame.drawRect(g,projectileX,projectileY);
        }

        private void translateProjectile()
        {
            this.projectileX -= 2;
        }

        public void updateCord()
        {
            this.projectileX = getX()-30;
            this.projectileY = getY()+53;
        }

    }
}
