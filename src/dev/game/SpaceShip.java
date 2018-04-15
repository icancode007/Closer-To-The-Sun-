package dev.game;

import java.awt.*;

public class SpaceShip extends Matter {
    private Image spaceShip = Toolkit.getDefaultToolkit().getImage("./res/Artwork/ship.png");

    public SpaceShip(float x, float y) {
        super(x, y);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(spaceShip,(int)x,(int)y,null);
    }

}
