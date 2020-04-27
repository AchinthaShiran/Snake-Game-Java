package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Board extends JPanel implements ActionListener {

    private int lengthX = 500;  //board lengthX
    private int lengthY = 500;  //board lengthY
    private Snake snake = new Snake();
    private JFrame frame;
    private Food food = new Food();

    public static void main(String[] args) {
        Board b = new Board(); //create Board
    }


    public Board() {

        DrawPanel panel = new DrawPanel();
        frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(lengthX, lengthY); // set frame size
        frame.addKeyListener(new Keys()); // get keys
        frame.setVisible(true);
        frame.add(panel); //add draw panel
        frame.setFocusable(true);
        food.spawn(); //spawn food

        while (snake.gameOver()) { //loop while game not over
            snake.move();
            snake.eat(food);
            snake.checkCollision(); //check for collisions,return false if collided
            frame.repaint();
        }
    }

    class DrawPanel extends JPanel {
        public void paint(Graphics g) {
            drawBoard(g);
            snake.drawSnake(g);
            displayGameOver(g);
            g.setColor(Color.YELLOW);
            g.fillRect(food.getX(), food.getY(), 20, 20);

            }

    }

    public void drawBoard(Graphics g) {      //draw the board
        g.setColor(Color.black); //set color
        g.fillRect(0, 0, lengthX, lengthY);      // draw rectangle to fill
        stats(g);       //draw stats(call function)

    }

    private void stats(Graphics g){
        g.setColor(Color.CYAN); //change color
        g.drawLine(0, lengthY - 100, lengthX, lengthY - 100);        // draw horizontal line
        g.drawString("x : " +(String.valueOf(snake.getX(0))), 10, lengthY - 50);         //print x coords

        Font f = new Font("Verdana", Font.BOLD, 20);    // set new font
        g.drawString("y : " +(String.valueOf(snake.getY(0))), 10, lengthY - 70);     //print y coords
        g.setFont(f); // set font to f
        g.drawString("Score : ",310,lengthY-61);    //print score

        g.drawString(String.valueOf(snake.getScore()),400,lengthY-60);
    }

    public void displayGameOver(Graphics g) {   //display if game over
        if (snake.gameOver() == false) {
            Font font = new Font("Verdana", Font.BOLD, 30);
            g.setFont(font);
            g.drawString("GAME OVER", 150, 150);

        }
    }


    private class Keys extends KeyAdapter { //actions done when keys pressed
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_RIGHT && snake.getDirection()!=Direction.LEFT)   //if pressed right,and current direction!=left,new direction = right
                snake.setDirection(Direction.RIGHT);
            if (key == KeyEvent.VK_LEFT && snake.getDirection()!=Direction.RIGHT)
                snake.setDirection(Direction.LEFT );
            if (key == KeyEvent.VK_UP && snake.getDirection()!=Direction.DOWN)
                snake.setDirection(Direction.UP);
            if (key == KeyEvent.VK_DOWN && snake.getDirection()!=Direction.UP)
                snake.setDirection(Direction.DOWN);

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
