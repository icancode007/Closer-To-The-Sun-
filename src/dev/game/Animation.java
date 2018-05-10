package dev.game;

import java.awt.*;

public class Animation {
    Image animations [];

    int delay;
    int duration;
    int current;

    public Animation(String file, int count, int duration)
    {
        animations = new Image[count];

        for(int i = 0; i < count; i++)
        {
            animations[i] = Toolkit.getDefaultToolkit().getImage(file+i+".png");
        }

        this.duration = duration;
        delay = duration;
    }
    public Image stillImage()
    {
        return animations[0];
    }

    public Image nextImage()
    {
        if(delay == 0)
        {
            current++;

            if(current == animations.length)
            {
                current = 1;
            }

            delay = duration;
        }
        else
        {
            delay--;
        }

        return animations[current];
    }
}

