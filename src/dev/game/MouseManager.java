package dev.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {
    private boolean leftPresed, rightPressed;
    private int mouseX, mouseY;

    private MenuManager menuManager;

    public MouseManager() {

    }

    public void setMenuManager(MenuManager menuManager) {
        this.menuManager = menuManager;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()  == MouseEvent.BUTTON1)
            leftPresed=true;
        else if (e.getButton() == MouseEvent.BUTTON3)
            rightPressed=true;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()  == MouseEvent.BUTTON1)
            leftPresed=false;
        else if (e.getButton() == MouseEvent.BUTTON3)
            rightPressed=false;
        if(menuManager != null) menuManager.onMouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if(menuManager != null) menuManager.onMouseMove(e);
    }

    public boolean isLeftPresed() {
        return leftPresed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setLeftPresed(boolean leftPresed) {
        this.leftPresed = leftPresed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
