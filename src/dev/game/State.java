package dev.game;

import java.awt.*;

public abstract class State {
    /*
    The following State Related methods could have eassily be a separate class or inside of another class
    since they're static it does not matter they would be accesible but lazyness puts them here.
    */

    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
    }
    public static State getState(){
        return currentState;
    }
    //Class
    protected Game game;

    public State(Game game){
        this.game = game;
    }

    public abstract void tick();
    public  abstract void render(Graphics g);

}
