package com.example.spaceinvaders;

import android.graphics.RectF;

public class Bullet {
    private float x;
    private float y;

    private RectF rect;

    // Which way is it shooting
    public int UP = 0;
    public int DOWN = 1;

    // Going nowhere
    int heading = -1;
    float speed = 650;

    private int width = 1;
    private int height;

    private boolean isActive;

    public Bullet(int screenY) {

        height = screenY / 20;
        isActive = false;
        this.rect = new RectF();
    }

    public RectF getRect(){
        return  rect;
    }

    public boolean getStatus(){
        return isActive;
    }

    public void setInactive(){
        isActive = false;
    }

    public float getImpactPointY(){
        if (heading == DOWN){
            return y + height;
        }else{
            return  y;
        }
    }

    public boolean shoot(float startX, float startY, int direction) {
        if (!isActive) {
            x = startX;
            y = startY;
            heading = direction;
            isActive = true;
            return true;
        }
        // Bullet already active
        return false;
    }

    public void update(long fps) {
        // Just move up or down
        if(heading == UP){
        y = y - speed / fps;
         }else{
             y = y + speed / fps;
        }

        // Update rect
        rect.left = x;
        rect.right = x + width;
        rect.top = y;
        rect.bottom = y + height;
    }
}
