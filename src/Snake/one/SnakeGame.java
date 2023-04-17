package com.atlassian;

public interface SnakeGame {
    //void setDirection(Direction direction);  //LEVEL 1
    //String render();  //LEVEL 2
    void moveSnake(Direction direction);
    boolean isGameOver();
}
