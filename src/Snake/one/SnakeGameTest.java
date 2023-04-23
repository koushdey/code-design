package com.atlassian.snake;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SnakeGameTest {

    /**
     * Rigorous Test :-)
     * 
     */
    @Test
    public void SnakeGameImplTest() throws InterruptedException
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
            Direction.LEFT);*/

        
        assertEquals(playSnakeGame(Direction.RIGHT), true);
    }

    private boolean playSnakeGame(Direction direction) throws InterruptedException{
        SnakeGameImpl snakeGame = new SnakeGameImpl();
        snakeGame.setDirection(direction);
        Thread.sleep(30000);
        snakeGame.setDirection(Direction.UP);
        return snakeGame.isGameOver();
    }
}

