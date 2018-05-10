package dev.game;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Random;

public class Asteroid extends Matter {
    private Image asteroidf;
    public boolean hit,stop;
    public Animation[] asteroid;
    static final int min =-500;
    static final int max = 300;
    static final int miny =1;
    static final int maxy = 500;
    public Rectangle meteorFrame;
    int widht, height;

    public Asteroid(Handler handler,String path,float x, float y, int width, int height) {
        super(handler,x, y,width,height);
        this.width = width;
        this.height = height;
        hit = false;
        stop = false;
        asteroidf = Toolkit.getDefaultToolkit().getImage(path);
        meteorFrame = new Rectangle(x,y,width,height);
    }
    public Asteroid(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y,width,height);
        this.width = width;
        this.height = height;
        hit = false;
        asteroidf = Toolkit.getDefaultToolkit().getImage("./res/Artwork/asteroids/aster1.png");
        asteroid = new Animation[9];
        for (int i = 0; i < 9 ; i++) {
            asteroid[i]=new Animation("./res/Artwork/asteroids/aster",8,10);
        }

        meteorFrame = new Rectangle(x,y,width,height);
    }

    public boolean gotHit(){
        return !this.hit;
    }

    public boolean colliding(Rectangle r ){
        return  (x  < r.posX +    r.w) &&
                (x  + widht > r.posX) &&
                (y  < r.posY +   r.h ) &&
                (y + width > r.posY);

    }
    @Override
    public void tick()
    {
        this.translate();
    }

    @Override
    public void render(Graphics g)
    {
        if(this.hit && !stop)
        {
            g.drawImage(asteroid[0].nextImage(),(int)x,(int)y,null);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            stop=true;
                        }
                    },
                    770
            );

        }
        if(!this.hit && !stop)
        {
            g.drawImage(asteroidf,(int)x,(int)y,null);
        }
        //meteorFrame.drawOval(g,(int)x+20,(int)y+15); //Here For Collition dectection
    }
    public void pushBack(){
        this.x -= 2;
    }
    public static int RanPosX()
    {
        Random x;
        x=new Random();
        int randomX = x.nextInt((max - min) + 1) + min;
        return randomX;
    }
    public static int RanPosY()
    {
        Random y;
        y=new Random();
        int randomY = y.nextInt((maxy - miny) + 1) + miny;
        return randomY;
    }

    private void translate()
    {
        this.x += 5;
    }
}
