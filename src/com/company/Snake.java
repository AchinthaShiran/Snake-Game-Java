package com.company;

import javax.swing.*;
import java.awt.*;

public class Snake extends JPanel {

    private final int MAX_LENGTH = 200;

    private int x[] = new int[MAX_LENGTH];
    private int y[] = new int[MAX_LENGTH];
    private int length = 2;
    private int score=0;

    private Direction direction = Direction.RIGHT;

    public Snake(){
        x[0]=20;    //initial X location of head
        y[0]=20;    //initial Y location of head
        x[1]=40;    //initial Y location of first part
        y[1]=20;    //initial Y location of first part


    }

    public void move(){

            if (direction == Direction.RIGHT)   //if direction == right, increment x of head by 20
                x[0]=x[0]+20;
            if (direction == Direction.LEFT)
                x[0]=x[0]-20;
            if (direction == Direction.UP)
                y[0]=y[0]-20;
            if (direction == Direction.DOWN)
                y[0]=y[0]+20;

            try{
                Thread.sleep(100);  //set time interval
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i=length;i>0;i--){ //loop through all parts
                x[i]=x[i-1];    //set x location of the current part to the location of the previous (ie. if current is last part, set that to the location of the part before)
                y[i]=y[i-1];

            }

    }

    public Direction getDirection() {
        return direction;
    }

    public void drawSnake(Graphics g){ //drawing the snake
        g.setColor(Color.cyan);
        g.fillRect(x[0],y[0],20,20);
        g.setColor(Color.green);
        for (int i =1;i<length;i++){    //add parts until i<length
            g.fillRect(x[i],y[i],20,20);
        }
    }

    public int getScore() {
        return score;
    }

    public boolean eat(Food food){
        if ((x[0]==food.getX()) && (y[0] == food.getY())){ //if head x,y == food x,y
            food.spawn();   //spawn food at new location
            length++;   //increment length
            score+=10;  //increment score
            return true;
        }
        return false;
    }

    public boolean gameOver(){
        if (x[0]<=0 || y[0]<=0 || x[0]>=460 || y[0]>=380)   //check if colllision with corners
        {
            return false;
        }
        if (checkCollision()){
            return false;
        }

        return true;
    }

    public boolean checkCollision(){    //check if snake eat tail
        for (int i=length;i>0;i--){
            if (i>4 && x[0]==x[i] && y[0]==y[i]){
                return true;
            }
//
        }
        return false;
    }

    public int getX(int i) {
        return x[i];
    }


    public int getY(int i) {
        return y[i];
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
