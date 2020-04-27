package com.company;

import java.util.Random;

public class Food {
    private int x ;
    private int y;

    public void spawn(){
        Random random = new Random();

         x = (random.nextInt(21)*20)+20;    // create random values between 20 - 440 X coordinates
         y = (random.nextInt(17)*20)+20;    // create random values between 20 - 340 Y coordinates
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

}
