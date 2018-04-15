package dev.game;

import java.awt.*;

public class GameState extends State {

    private  SpaceShip redSpaceShip;

    public GameState(Game game){
        super(game);
        redSpaceShip = new SpaceShip(800,20);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        redSpaceShip.render(g);
    }
}
