package dev.game;

import java.awt.*;
import java.awt.event.*;
import sun.awt.image.ToolkitImage;
import java.awt.image.BufferStrategy;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;


public class Game implements Runnable, KeyListener{

    private Display display;
    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private KeyManager keymanager;
    private Graphics g;

    private Ship redship = new Ship(800,20);


    boolean sp_pressed = false;
    boolean lt_Pressed = false;
    boolean rt_Pressed = false;
    boolean up_Pressed = false;
    boolean dn_Pressed = false;

    //States
    private State gameState;
    private State menuState;
    private State settings;

    public Game(String title, int width, int height)
    {
        this.width = width;
        this.height=height;
        this.title = title;
    }

    private void init()
    {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keymanager);
        gameState = new GameState(this);
        menuState = new GameState(this);
        State.setState(gameState);

//        display.getFrame().addKeyListener(this);
//        addKeyListener(this);

    }
    int x=0;
    private void tick()
    {
     if(State.getState() != null)
        {
            State.getState().tick();
        }
    }

    private void render()
    {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null)
            {
                display.getCanvas().createBufferStrategy(3);
                return;
            }
        g = bs.getDrawGraphics();
        //clear Screen
        g.clearRect(0,0,width,height);

        //draw here
        if(State.getState() != null){
            State.getState().render(g);
        }
      /*
        redship.drawShip(g);
        if(redship.shooting)
        {
            if (redship.lasers.size() != 0)
            {
                    redship.lasers.get(redship.getLaserCount()).drawProjectile(g);
                    redship.lasers.get(redship.getLaserCount()).translateProjectile();
            }
        }
        */

        //end Drawing
        bs.show();
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        switch(code)
        {
            case KeyEvent.VK_DOWN:
                dn_Pressed = true;
                break;
            case KeyEvent.VK_UP:
                up_Pressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rt_Pressed = true;
                break;
            case KeyEvent.VK_LEFT:
                lt_Pressed = true;
                break;
            case KeyEvent.VK_SPACE:
                sp_pressed = true;
                 break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        switch(code)
        {
            case KeyEvent.VK_DOWN:
                dn_Pressed = false;
                break;
            case KeyEvent.VK_UP:
                up_Pressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rt_Pressed = false;
                break;
            case KeyEvent.VK_LEFT:
                lt_Pressed = false;
                break;
            case KeyEvent.VK_SPACE:
                sp_pressed = false;
                break;
        }
    }
    @Override
    public void run()
    {
        init();
        int fps = 60;
        double timePerTick =1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks =0;
        while(running)
        {
            now = System.nanoTime();
            delta +=(now - lastTime) / timePerTick;
            lastTime = now;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1)
            {
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000)
            {
                System.out.println("Ticks and Frames"+ ticks);
                ticks = 0;
                timer = 0;
            }
//            if(sp_pressed)
//            {
//                redship.lasers.get(redship.getLaserCount()).updateCord();
//                redship.shoot();
//
//            }
//            if(lt_Pressed)
//            {
//                redship.moveForward(2);
//            }
//            if(rt_Pressed)
//            {
//                redship.moveBackward(2);
//            }
//            if(up_Pressed)
//            {
//                redship.moveUp(2);
//            }
//            if(dn_Pressed)
//            {
//                redship.moveDown(2);
//            }
        }
        stop();
    }




    public synchronized void  start ()
    {
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()
    {
        if(!running) return;
        running = false;
        try
        {
            thread.join();
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

}
