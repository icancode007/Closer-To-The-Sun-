package dev.game;

import java.awt.*;
import java.util.ArrayList;

import sun.awt.image.ToolkitImage;

public class Ship {
    public static double xPos, yPos;

    double dx=0;
    double dy=0;

    boolean shooting= false;
    boolean moving = false;
    private final int h = 20;
    private final int w = 20;
    private int laserCount=0;

    private Image shipImg = Toolkit.getDefaultToolkit().getImage("./res/Artwork/ship.png");
    Rectangle laserGun    = new Rectangle(this.xPos,this.yPos, h, w);
    Rectangle mainGun     = new Rectangle(this.xPos ,this.yPos,  h, w);
    Rectangle topTurbine  = new Rectangle(this.xPos, this.yPos,  h, w);
    Rectangle botTurbine  = new Rectangle(this.xPos, this.yPos,  h, w);
    ArrayList<Projectiles> lasers = new ArrayList<>();

    public Ship(double xPos, double yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        lasers.add(new Projectiles());
        lasers.add(new Projectiles());
        lasers.add(new Projectiles());
    }

    public int getLaserCount() {
        return laserCount;
    }
    public int increaseLaserCount() {
        return laserCount +=1;
    }
    public void moveForward(int chgX)
    {
        this.moving = true;
        this.xPos -= (double)chgX;

    }
    public void moveBackward(int chgX)
    {
        this.moving = true;
        this.xPos += (double)chgX;
    }
    public void moveDown(int chgY)
    {
        this.moving = true;
        this.yPos+=chgY;
    }
    public void moveUp(int chgY)
    {
        this.moving = true;
        this.yPos-=chgY;
    }

    public static double getxPos()
    {
        return xPos;
    }

    public static double getyPos()
    {
        return yPos;
    }

    public void drawShip(Graphics g)
    {
        if (this.moving) {
            g.drawImage(shipImg, (int) xPos, (int) yPos, null);
            mainGun.drawRect(g,xPos+60,yPos+ 50);
            laserGun.drawRect(g,xPos,yPos+50);
            topTurbine.drawRect(g,xPos+120,yPos + 6);
            botTurbine.drawRect(g,xPos+120,yPos + 92);

        }else{
            g.drawImage(shipImg, (int) xPos, (int) yPos, null);

        }
        this.moving = false;
    }

    public boolean shoot()
    {
        shooting = !shooting;
        return !shooting;
    }
    class Projectiles
    {

        private Image projectile= Toolkit.getDefaultToolkit().getImage("./res/Artwork/redLaser.png");

        public double proX;
        public double proY;
        private double ax=0;
        private double ay=0;
        private double vx=0;
        private double vy=0;

        public Projectiles()
        {
            this.proX = Ship.getxPos()-30;
            this.proY = Ship.getyPos()+53;
        }

        public void drawProjectile(Graphics g)
        {
            g.drawImage(projectile,(int)proX,(int)proY,null);
        }
        public void setVelocity(double vx, double vy)
        {
            this.vx=vx;
            this.vy=vy;
        }
        public void setAcceleration(double ax, double ay)
        {
            this.ax=ax;
            this.ay=ay;
        }
        public void translateProjectile()
        {
//            vx += ax;
//            vy += ay;
            this.proX -= 5;
        }
        public void setImage(String newPath)
        {
            projectile=Toolkit.getDefaultToolkit().getImage(newPath);
        }
        public void updateCord()
        {
            this.proX = Ship.getxPos()-30;
            this.proY = Ship.getyPos()+53;
        }

    }
}
