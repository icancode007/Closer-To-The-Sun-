package dev.game;

import java.util.logging.Handler;

public class GameCam {
//    private Handler handler;
    private float xOffSet, yOffSet;

    public GameCam(float xOffSet) {
        this.xOffSet = xOffSet;
    }

   /* public void checkAvailableSpace(){
        if(xOffSet < 0)
        {

        }
        else if(xOffSet > handler.get)
    }
*/
    public float getxOffSet()
    {
        return xOffSet;
    }

    public void setxOffSet(float xOffSet) {
        this.xOffSet = xOffSet;
    }


    public void move(float xAmt)
    {
        xOffSet += xAmt;
    }
}
