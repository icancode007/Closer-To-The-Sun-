package dev.game;

import java.awt.*;

public class GameState extends State {

    private  SpaceShip redSpaceShip;
    private  SpaceGround spaceField;
    private  SpaceGround starField;
    private  SpaceGround starFieldz;

    public GameState(Game game){
        super(game);
        redSpaceShip = new SpaceShip(game,800,20);
        spaceField = new SpaceGround("./res/Artwork/farback.gif",0,0);
        starField = new SpaceGround("./res/Artwork/starfield.png",0,0);
        starFieldz = new SpaceGround("./res/Artwork/starfield.png",-1000,0);
    }

    @Override
    public void tick() {
        redSpaceShip.tick();
        starField.tick();
        if(starField.getX() > 1000){
            starField.setX(-1000);
        }

            starFieldz.tick();
        if (starFieldz.getX()>1000){
            starFieldz.setX(-1000);
        }

    }

    @Override
    public void render(Graphics g) {
        spaceField.render(g);
        starField.render(g);
        redSpaceShip.render(g);
        if(starField.getX() > 300){
            starFieldz.render(g);
        }

    }
}
