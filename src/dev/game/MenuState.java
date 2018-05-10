package dev.game;

import java.awt.*;

public class MenuState extends State
{
    private SpaceGround menuGround;

    private MenuManager menuManager;
     ClickListener noClick = new ClickListener() {
         @Override
         public void onClick() {

         }
     };
    ButtonImage menuHeader;

    public MenuState(Handler handler)
    {
        super(handler);
        menuManager = new MenuManager(handler);
        handler.getMouseManager().setMenuManager(menuManager);

        menuGround = new SpaceGround("./res/Artwork/Menu/menuground.png",0,0,handler);

        menuHeader= new ButtonImage("./res/Artwork/Menu/menuHeader.png",420, 100,200,100,noClick);
        menuHeader.noLineAtHover();


        menuManager.addObj(menuHeader);

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
//        if(handler.getMouseManager().isLeftPresed()&& handler.getMouseManager().isRightPressed()){
//            State.setState(handler.getGame().gameState);
//        }
        menuManager.tick();
    }

    @Override
    public void render(Graphics g)
    {
        menuGround.render(g);

        menuManager.render(g);
    }
}
