package dev.game;

import java.awt.*;

public class ButtonImage extends MenuObject{
    private ClickListener clicker;
    private Image img;

    private int hoverX1 = (int)layer.getX()+20;
    private int hoverX2 = (int)layer.getX()+layer.width-20;
    private int hoverY1Y2 =  (int)layer.getY()+layer.height-40;

    public ButtonImage(String path, double x, double y, int width,int height, ClickListener clicker)
    {
        super(x, y,width,height);
        this.clicker = clicker;
        this.img = Toolkit.getDefaultToolkit().getImage(path);
        this.w = width;
        this.h = height;
    }
    @Override
    public void render(Graphics g)
    {

            g.setColor(Color.ORANGE);
            g.drawImage(img,(int)x,(int)y,w,h,null);
            //        g.drawRect((int)layer.getX(),(int)layer.getY(),w,h);
            if(this.hovering)
                g.drawLine(hoverX1,hoverY1Y2,hoverX2,hoverY1Y2);
    }

    @Override
    public void tick()
    { }

    @Override
    public void onClick() {
        clicker.onClick();
    }

    public void noLineAtHover(){
        this.hoverX1=0;
        this.hoverX2=0;
        this.hoverY1Y2=0;
    }
}
