package dev.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SpaceShip extends Matter
{
    private Game game;
    private  Rectangle shipFrame,shipGunFrame;
    private Image spaceShip = Toolkit.getDefaultToolkit().getImage("./res/Artwork/ship.png");
    private Animation[] flames, smFlames;

    boolean shooting = false;
    public Projectiles [] lasers = new Projectiles[1000];
    private int munitionCount=1;

    public void setMunitionCount(int munitionCount)
    {
        this.munitionCount = munitionCount;
    }

    public SpaceShip(Game game, float x, float y)
    {
        super(x, y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        flames = new Animation[6];
        smFlames = new Animation[6];
        this.game = game;
        shipFrame = new Rectangle(x,y,120,80);
        shipGunFrame= new Rectangle(x,y,50,50);;
        loadProjectiles();
        for(int i = 0; i<6;i++)
        {
            flames[i]=new Animation("./res/Artwork/redflame",5,10);
            smFlames[i]=new Animation("./res/Artwork/littlef",5,10);
        }
    }
    public void loadProjectiles()
    {
        for(int i=0;i<lasers.length;i++){
            lasers[i] = new Projectiles();
        }
    }
    @Override
    public void tick()
    {
        getInput();
        move();

    }

    public void getInput(){
        xMove = 0;
        yMove = 0;
        if(game.getKeyManager().up)
        {
            yMove = -speed;
        }
        if(game.getKeyManager().down)
        {
            yMove = speed;
        }
        if(game.getKeyManager().left)
        {
            xMove = -speed;
        }
        if(game.getKeyManager().right)
        {
            xMove = +speed;
        }
        if(game.getKeyManager().spaceBar)
        {
            shoot();
        }
    }

    @Override
    public void render(Graphics g)
    {
        /*
        If there was the need to resize my spaceship I can always pass 2 more values in the
        method below
         */
        g.drawImage(spaceShip,(int)x,(int)y,null);
//        g.drawImage(flames[0].nextImage(),   (int)x + 120, (int)y + 3, null);
//        g.drawImage(flames[0].nextImage(),   (int)x + 120, (int)y + 89, null);
        g.drawImage(smFlames[0].nextImage(), (int)x + 129, (int)y + 40, null);
        g.drawImage(smFlames[0].nextImage(), (int)x + 129, (int)y + 69, null);
        /*
            g.setColor(Color.WHITE);
            shipFrame.drawRect(g,x+60,y);//Just here for Collition detection
            shipGunFrame.drawRect(g,x+5,y+40);//Just here for Collition detection
        */
        if(shooting ||(lasers[munitionCount].getProX()> 1 && lasers[munitionCount].getProX()< 1000))
        {
            for (Projectiles i :lasers)
            {
                i.drawProjectile(g);
            }
        }

    }

    public void shoot()
    {
        if(munitionCount > 99) setMunitionCount(0);
        shooting=true;
        munitionCount++;
        lasers[munitionCount].updateCord();
        shooting=false;
    }

    class Projectiles
    {

        private Image projectile= Toolkit.getDefaultToolkit().getImage("./res/Artwork/redLaser.png");

        public double proX;
        public double proY;
        private Rectangle laserBind;
        private Random rnd = new Random();
        private int r = rnd.nextInt() % 5;
        public double getProX() {
            return proX;
        }


        public Projectiles()
        {
            this.proX = getX()-30;
            this.proY = getY()+53;
            laserBind = new Rectangle(this.proX,this.proY,15,53);
        }

        public void drawProjectile(Graphics g)
        {
            g.drawImage(projectile,(int)proX,(int)proY,null);
            this.translateProjectile();
        }
        private void translateProjectile()
        {
            this.proX -= 5;
        }
        public void setImage(String newPath)
        {
            projectile=Toolkit.getDefaultToolkit().getImage(newPath);
        }
        public void updateCord()
        {
            this.proX = getX()-30;
            this.proY = getY()+53;
        }

    }

    class Turbines{

    }
}
