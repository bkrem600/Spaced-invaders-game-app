package com.example.spaceinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import java.util.Random;

public class Invader {
    RectF rect;

    Random generator = new Random();

    // The player ship will be represented by a Bitmap
    private Bitmap bitmap1;
    private Bitmap bitmap2;

    // How long and high our invader will be
    private float length;
    private float height;

    // X is the far left of the rectangle which forms our invader
    private float x;

    // Y is the top coordinate
    private float y;

    // This will hold the pixels per second speed that the invader will move
    private float defenderSpeed;

    public final int LEFT = 1;
    public final int RIGHT = 2;

    // Is the ship moving and in which direction
    private int defenderMoving = RIGHT;

    boolean isVisible;

    public Invader(Context context, int row, int column, int screenX, int screenY) {
        // Initialize a blank RectF
        rect = new RectF();

        length = screenX / 20;
        height = screenY / 20;

        isVisible = true;

        int padding = screenX / 25;

        x = column * (length + padding);
        y = row * (length + padding/4);

        // Initialize the bitmap
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.invader1);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.invader2);

        // stretch the first bitmap to a size appropriate for the screen resolution
        bitmap1 = Bitmap.createScaledBitmap(bitmap1,
                (int) (length),
                (int) (height),
                false);

        // stretch the first bitmap to a size appropriate for the screen resolution
        bitmap2 = Bitmap.createScaledBitmap(bitmap2,
                (int) (length),
                (int) (height),
                false);

        // How fast is the invader in pixels per second
        defenderSpeed = 40;
    }

    public void setInvisible(){
        isVisible = false;
    }

    public boolean getVisibility(){
        return isVisible;
    }

    public RectF getRect(){
        return rect;
    }

    public Bitmap getBitmap(){
        return bitmap1;
    }

    public Bitmap getBitmap2(){
        return bitmap2;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getLength(){
        return length;
    }

    public void update(long fps){
        if(defenderMoving == LEFT){
            x = x - defenderSpeed / fps;
        }

        if(defenderMoving == RIGHT){
            x = x + defenderSpeed / fps;
        }

        // Update rect which is used to detect hits
        rect.top = y;
        rect.bottom = y + height;
        rect.left = x;
        rect.right = x + length;
    }

    public void dropDownAndReverse(){
        if(defenderMoving == LEFT){
            defenderMoving = RIGHT;
        }else{
            defenderMoving = LEFT;
        }
        y = y + height;
        defenderSpeed = defenderSpeed * 1.18f;
    }

    public boolean takeAim(float defenderX, float defenderLength){
        int randomNumber = -1;
        // If near the player
        if((defenderX + defenderLength > x &&
                defenderX + defenderLength < x + length) || (defenderX > x && defenderX < x + length)) {
            // A 1 in 500 chance to shoot
            randomNumber = generator.nextInt(150);
            if(randomNumber == 0) {
                return true;
            }
        }
        // If firing randomly (not near the player) a 1 in 5000 chance
        randomNumber = generator.nextInt(2000);
        if(randomNumber == 0){
            return true;
        }
        return false;
    }
}
