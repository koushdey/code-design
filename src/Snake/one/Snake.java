package com.atlassian;

import java.util.Arrays;
import java.util.List;

/**
 * Snake Game
 *
 */
public class Snake 
{
    public static void main( String[] args ) throws InterruptedException
    {
        List<Direction> directions = Arrays.asList(
            Direction.RIGHT,
            Direction.RIGHT,
            Direction.RIGHT,
            Direction.UP,
            Direction.UP,
            Direction.LEFT,
            Direction.DOWN,
            Direction.RIGHT,
            Direction.UP,
            Direction.LEFT);
        Snake.playSnakeGame(directions);

        //Snake.playSnakeGame(Direction.RIGHT);  //LEVEL 1 & 2
    }

    private static void playSnakeGame(List<Direction> directions) throws InterruptedException{
    //private static void playSnakeGame(Direction direction) throws InterruptedException{  //LEVEL 1 & 2
        SnakeGameImpl snakeGame = new SnakeGameImpl();
        for(Direction direction : directions){  //from
            snakeGame.moveSnake(direction);
            if(snakeGame.isGameOver()){
                System.out.println("Snake Collided!! Game Over :( ");
                return;
            }
            Thread.sleep(1000);
        }                                      //to
        
        /*
        snakeGame.setDirection(direction);      //LEVEL 1 & 2
        for(int i=0; i < 60 ; i++){
            Thread.sleep(1000);
            snakeGame.setDirection(direction);
            System.out.println(snakeGame.render()); //LEVEL 2 AVOID
            if(snakeGame.isGameOver()){
                System.out.println("Snake Collided!! Game Over :( ");
                return;
            }
        }
        */
         
    }

}
