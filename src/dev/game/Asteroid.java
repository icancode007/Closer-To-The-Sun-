package dev.game;

import sun.awt.resources.awt;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Random;

import static dev.game.GameState.score;

public class Asteroid extends Matter
{
    private Image asteroidf;
    public boolean hit,stopDRAWING,drawItem;
    public Animation[] asteroid;
    static final int min =-5500;
    static final int max = 300;
    static final int miny =1;
    static final int maxy = 500;

    public HashMap<String,Integer> droppables;
//    public Item ranItem;

    private Random rnd = new Random();
    public int ranDroppable = rnd.nextInt(100)+1;
    public Integer ranDuration = rnd.nextInt(3)+1;


    private  int [] pcts = new int [100];
    //Later make this array available somewhere else so that it doesn't have to be pooulated in every instance

    public Rectangle meteorFrame;
    int width, height;

    public Asteroid(Handler handler,String path,float x, float y, int width, int height)
    {
        super(handler,x, y,width,height);
        this.width = width;
        this.height = height;
        hit = false;
        this.stopDRAWING = false;
        this.drawItem = false;
//        ranItem = new Item((int)this.x,(int)this.y,this.ranDuration,this.droppables);
        asteroidf = Toolkit.getDefaultToolkit().getImage(path);
    }
    public Asteroid(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y,width,height);
        this.width = width;
        this.height = height;
        hit = false;
        asteroidf = Toolkit.getDefaultToolkit().getImage("./res/Artwork/asteroids/aster1.png");
        asteroid = new Animation[9];
        for (int i = 0; i < 9 ; i++) {
            asteroid[i]=new Animation("./res/Artwork/asteroids/aster",8,10);
        }

        this.droppables = new HashMap<>();
        this.hit = false;
        for (int i = 0; i < pcts.length ; i++) {
            pcts[i] = i;
        }

        if(this.ranDroppable <=19){ this.droppables.put("Energy",ranDuration);
        }
        if(this.ranDroppable >19 && this.ranDroppable <= 31) this.droppables.put("Boost",ranDuration);

        if(this.ranDroppable >31 && this.ranDroppable <= 49) this.droppables.put("Shield",ranDuration);

        if(this.ranDroppable > 49) this.droppables.put("Nothing",0);


    }

    public boolean gotHit()
    {
        return !this.hit;
    }

    public boolean collide(Rectangle o )
    {
        return  (x  < o.getX() +    o.width) &&
                (x  + width >  o.getX()) &&
                (y  < o.getY() +   o.height ) &&
                (y + width > o.getY());

    }

    @Override
    public void tick()
    {
        this.translate();
    }

    @Override
    public void render(Graphics g)
    {
        if(this.hit && !stopDRAWING)
        {
            erasecCollition(this);
            g.drawImage(asteroid[0].nextImage(),(int)x,(int)y,null);
//            ranItem.drawItem(g);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run()
                        {
                            stopDRAWING=true;
                            drawItem = true;
                        }
                    },
                    770
            );
            score++;
            System.out.println("SCORE:"+score);
            return;
        }
        if(!this.hit && !stopDRAWING)
        {
            g.drawImage(asteroidf,(int)x,(int)y,null);
            meteorFrame = new Rectangle((int)x,(int)y,width,height);
        }
        if(this.drawItem){

        }
    }
    public void pushBack()
    {
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
        this.x += 4;
    }

    private void erasecCollition(Asteroid a)
    {
        a.meteorFrame = new Rectangle(0,0,0,0);
    }

    /*
    class Item
    {
        private int itemX,itemY,duration;
        int  animSize = 6;
        public Animation [] itemA;
        Rectangle itemFrame;
        HashMap<String,Integer> itemSet;

        public Item(int x, int y, int duration, HashMap<String,Integer> item)
        {
            this.itemX = x;
            this.itemY = y;
            this.duration = duration;
            itemA = new Animation[6];
            this.itemSet = item;

            System.out.println("called Const");

            itemA = item.containsKey("Energy") ? itemAnim("./res/Artwork/Item/Energy"):
                    item.containsKey("Boost")  ? itemAnim("./res/Artwork/Item/Boost") :
                    item.containsKey("Shield") ? itemAnim("./res/Artwork/Item/Shield"):
                    itemAnim("Nothing");
        }

        private Animation[] itemAnim(String path){
            System.out.println("called");
            itemA = new Animation[6];
            for(int i = 0; i < 5; i++){
              itemA [i] = new Animation(path,animSize,10);
            }
            return itemA;
        }
        public void drawItem(Graphics g){
            g.drawImage(itemA[0].nextImage(),itemX,itemY,null);
            this.itemFrame = new Rectangle(itemX,itemY,40,40);

        }
    }*/
}
