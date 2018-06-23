package dev.game;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

import static dev.game.GameState.*;

public class SpaceShip extends Matter
{
    public   Rectangle shipFrame,shipGunFrame;
    private Image spaceShip = Toolkit.getDefaultToolkit().getImage("./res/Artwork/ship.png");
    private Animation[] flames, smFlames, shpExp;
    private ArrayList<Rectangle> redSpBar;
    public boolean depletedEnergy, stopDRAWING;
    public ArrayList<Projectiles> drawnlaser= new ArrayList<>();
    public int munitionCount=-1;
    public int timer=0;
    public int scoreTimer=0;

    public SpaceShip(Handler handler, float x, float y)
    {
        super(handler, x, y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        flames = new Animation[6];
        smFlames = new Animation[6];
        shpExp = new Animation[7];
        redSpBar = new ArrayList<>();
        this.setEnergy(1000);
        this.depletedEnergy = false;
        this.stopDRAWING = false;

        for(int i = 0; i<6;i++)
        {
            flames[i]=new Animation("./res/Artwork/flames/redflame",5,10);
            smFlames[i]=new Animation("./res/Artwork/flames/littlef",5,10);
        }
        for (int i = 100; i < this.energy ; i+=100) {
            redSpBar.add(new Rectangle(850+(i)/10,10,10,10));
        }
        for (int i = 0; i <7 ; i++) {
            shpExp[i]=new Animation("./res/Artwork/goodSexp/boom",6,14);
        }
    }

    @Override
    public void tick()
    {
        getInput();
        if(this.x > 50);
        move();
        timer--;
        scoreTimer--;
    }

    public void getInput()
    {
        //variables fty,bby,btx are being used to check the bounds that the spaceship cant go through

        xMove = 0;
        yMove = 0;
        if(handler.getKeyManager().up)
        {
            if(this.y > fty ) yMove = -speed;

        }
        if(handler.getKeyManager().down)
        {
            if(this.y < bby-120) yMove = speed;

        }
        if(handler.getKeyManager().left)
        {
            //this line will need some more logic to it because it will be working intermintently in each wave
            if (this.x > 400)
                xMove = -speed;
        }
        if(handler.getKeyManager().right)
        {
            if(this.x < btx-140) xMove = +speed;
        }
        if(handler.getKeyManager().spaceBar)
        {
            shoot();
        }
    }


    @Override
    public void render(Graphics g)
    {

        if(this.depletedEnergy && !stopDRAWING)
        {
            //            erasecCollition(this);
            g.drawImage(shpExp[0].nextImage(),(int)x,(int)y,null);

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run()
                        {
                            stopDRAWING=true;
                            //call GAME OVER STATE HERE
                            State.setState(handler.getGame().gameOverState);
                        }
                    },
                    900
            );
            while (scoreTimer <= 0){
                score++;
                scoreTimer = 10;
            }

        }
        if(!this.depletedEnergy && !stopDRAWING)
        {
            g.drawImage(spaceShip,(int)x,(int)y,null);
            g.drawImage(smFlames[0].nextImage(), (int)x + 129, (int)y + 40, null);
            g.drawImage(smFlames[0].nextImage(), (int)x + 129, (int)y + 69, null);
                //g.drawImage(flames[0].nextImage(),   (int)x + 120, (int)y + 3, null);
                //g.drawImage(flames[0].nextImage(),   (int)x + 120, (int)y + 89, null);
        }
        //COLLITION FRAMES
        shipFrame = new Rectangle((int)x,(int)y,120,80);
        shipGunFrame= new Rectangle((int)x,(int)y,50,50);

        //SHOTS
        for(Projectiles l : drawnlaser ){
            if(l != null)
                l.drawProjectile(g);

            else drawnlaser.get(munitionCount).drawProjectile(g);
        }
        //ENERGY BAR STATEMENTS
        g.setColor(Color.orange);
        g.drawRect(860,10,90,10);
        if(this.energy == 1000){
            for (int i = 0; i <redSpBar.size() ; i++) {
                g.fillRect((int) redSpBar.get(i).getX(), (int) redSpBar.get(i).getY(), (int) redSpBar.get(i).getWidth(), (int) redSpBar.get(i).getHeight());
            }
        }
        if(this.energy < 1000 && this.energy > 900){
            for (int i = 0; i <redSpBar.size() ; i++)
                g.fillRect((int)redSpBar.get(i).getX(),(int)redSpBar.get(i).getY(),(int)redSpBar.get(i).getWidth(),(int)redSpBar.get(i).getHeight());
        }

        if(this.energy < 900 && this.energy > 800)
        {
            for (int i = 0; i <redSpBar.size()-1 ; i++)
                g.fillRect((int)redSpBar.get(i).getX(),(int)redSpBar.get(i).getY(),(int)redSpBar.get(i).getWidth(),(int)redSpBar.get(i).getHeight());
        }
        if(this.energy < 800 && this.energy > 700)
        {
            for (int i = 0; i <redSpBar.size()-2 ; i++)
                    g.fillRect((int)redSpBar.get(i).getX(),(int)redSpBar.get(i).getY(),(int)redSpBar.get(i).getWidth(),(int)redSpBar.get(i).getHeight());
        }
        if(this.energy < 700 && this.energy > 600)
        {
                for (int i = 0; i <redSpBar.size()-3 ; i++)
                    g.fillRect((int)redSpBar.get(i).getX(),(int)redSpBar.get(i).getY(),(int)redSpBar.get(i).getWidth(),(int)redSpBar.get(i).getHeight());
        }
        if(this.energy < 600 && this.energy > 500)
        {
            for (int i = 0; i <redSpBar.size()-4 ; i++)
                    g.fillRect((int)redSpBar.get(i).getX(),(int)redSpBar.get(i).getY(),(int)redSpBar.get(i).getWidth(),(int)redSpBar.get(i).getHeight());
        }
        if(this.energy < 500 && this.energy > 400)
        {
            for (int i = 0; i <redSpBar.size()-5 ; i++)
                    g.fillRect((int)redSpBar.get(i).getX(),(int)redSpBar.get(i).getY(),(int)redSpBar.get(i).getWidth(),(int)redSpBar.get(i).getHeight());
        }
        if(this.energy < 400 && this.energy > 300){
            for (int i = 0; i <redSpBar.size()-6 ; i++)
                    g.fillRect((int)redSpBar.get(i).getX(),(int)redSpBar.get(i).getY(),(int)redSpBar.get(i).getWidth(),(int)redSpBar.get(i).getHeight());
        }
        if(this.energy < 300 && this.energy > 200){
            for (int i = 1; i <redSpBar.size()-7 ; i++)
                    g.fillRect((int)redSpBar.get(i).getX(),(int)redSpBar.get(i).getY(),(int)redSpBar.get(i).getWidth(),(int)redSpBar.get(i).getHeight());
        }
        if(this.energy < 100){
            for (int i = 0; i <redSpBar.size()-8 ; i++)
                g.fillRect((int)redSpBar.get(i).getX(),(int)redSpBar.get(i).getY(),(int)redSpBar.get(i).getWidth(),(int)redSpBar.get(i).getHeight());
        }


        //collitionTest
            /*
                g.setColor(Color.WHITE);
                g.drawRect((int)shipFrame.getX()+60,(int)shipFrame.getY(),110,120);
                g.drawRect((int)shipGunFrame.getX()+5,(int)shipFrame.getY()+40,50,50);
            */
    }

    public void shoot()
    {
        if(munitionCount < 999 )
        {

            while (timer <= 0){
                munitionCount++;
                drawnlaser.add(new Projectiles());
                timer=10;
            }

        }

    }

    class Projectiles
    {

        private Image projectile= Toolkit.getDefaultToolkit().getImage("./res/Artwork/redLaser.png");

        public  double projectileX;
        public  double projectileY;
        public  boolean hit ;

        public Rectangle laserFrame;

        public Projectiles()
        {
            this.projectileX = getX()-30;
            this.projectileY = getY()+53;

        }

        public boolean itHit()
        {
            return this.hit=true;
        }
        public void setImage(String newPath)
        {
            projectile=Toolkit.getDefaultToolkit().getImage(newPath);
        }

        public double getProjectileX()
        {
            return projectileX;
        }

        public void setProjectileY(double projectileY)
        {
            this.projectileY = projectileY;
        }

        public boolean collide(java.awt.Rectangle o )
        {
            return  (x  < o.getX() +  o.width) &&
                    (x  + width >  o.getX()) &&
                    (y  < o.getY() +   o.height ) &&
                    (y + width > o.getY());
        }

        public void drawProjectile(Graphics g)
        {
            g.drawImage(projectile,(int)projectileX,(int)projectileY,null);
            laserFrame = new Rectangle((int)this.projectileX,(int)this.projectileY,15,15);
            this.translateProjectile();
        }

        private void translateProjectile()
        {
            this.projectileX -= 7;
        }
    }
}
