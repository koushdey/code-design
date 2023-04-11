package com.atlassian;

//import java.util.Arrays;
//import java.util.List;

/**
 * Snake Game
 *
 */
public class Snake 
{
    public static void main( String[] args ) throws InterruptedException
    {
        /*List<Direction> directions = Arrays.asList(
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
        App.playSnakeGame(directions);
        */

        Snake.playSnakeGame(Direction.RIGHT);
    }

    /*private static void playSnakeGame(List<Direction> directions) throws InterruptedException{
        SnakeGameImpl snakeGame = new SnakeGameImpl();
        for(Direction direction : directions){
            snakeGame.moveSnake(direction);
            if(snakeGame.isGameOver()){
                System.out.println("Snake Collided!! Game Over :( ");
                return;
            }
            Thread.sleep(1000);
        }
    } */

    private static void playSnakeGame(Direction direction) throws InterruptedException{
        SnakeGameImpl snakeGame = new SnakeGameImpl();
        snakeGame.setDirection(direction);
        for(int i=0; i < 60 ; i++){
            Thread.sleep(1000);
            snakeGame.setDirection(direction);
            if(snakeGame.isGameOver()){
                System.out.println("Snake Collided!! Game Over :( ");
                return;
            }

        }
    } 
}
