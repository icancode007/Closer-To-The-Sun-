package dev.game;

import java.awt.*;

public class MenuState extends State
{
    private SpaceGround menuGround;

    private MenuManager menuManager;

    private Image logoHeader;


    public MenuState(Handler handler)
    {
        super(handler);

        menuManager = new MenuManager(handler);
        handler.getMouseManager().setMenuManager(menuManager);

        menuGround = new SpaceGround("./res/Artwork/Menu/menuground.png",0,0,handler);

        logoHeader = Toolkit.getDefaultToolkit().getImage("./res/Artwork/Menu/menuHeader.png");

        menuManager.addObj(new ButtonImage("./res/Artwork/Menu/PlayButton.png", 420, 200,175,90, new ClickListener() {
            @Override
            public void onClick()
            {
                State.setState(handler.getGame().gameState);

            }
        }));

        menuManager.addObj(new ButtonImage("./res/Artwork/Menu/options.png", 420, 320,185,100, new ClickListener() {
            @Override
            public void onClick()
            {
                //Will set  this up so that it can give option
            }

        }));
        menuManager.addObj(new ButtonImage("./res/Artwork/Menu/rules.png", 420, 440, 185, 90, new ClickListener() {
            @Override
            public void onClick()
            {
                //Will set This Up so that it can show a box with the game rules
            }
        }));

    }

    @Override
    public void tick()
    {
        menuManager.tick();

    }

    @Override
    public void render(Graphics g)
    {
        menuGround.render(g);
        g.drawImage(logoHeader,420, 100,200,100,null);
        menuManager.render(g);
    }
}
