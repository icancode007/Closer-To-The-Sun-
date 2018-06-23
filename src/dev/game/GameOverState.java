package dev.game;

import javafx.scene.paint.Color;

import java.awt.*;

public class GameOverState extends State {
    private MenuManager menuManager;

    public GameOverState(Handler handler) {
        super(handler);
        menuManager = new MenuManager(handler);
        handler.getMouseManager().setMenuManager(menuManager);

        menuManager.addObj(new ButtonImage("./res/Artwork/continue.png", 420, 200,175,90, new ClickListener() {
            @Override
            public void onClick()
            {
                State.setState(handler.getGame().gameState);
            }
        }));
        menuManager.addObj(new ButtonImage("./res/Artwork/quit.png", 420, 300,175,90, new ClickListener() {
            @Override
            public void onClick()
            {

            }
        }));



    }

    @Override
    public void tick() {
        menuManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0,0,1000,600);
        menuManager.render(g);

    }
}
