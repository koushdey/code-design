package com.atlassian;

//import java.time.Duration;
import java.util.Deque;
import java.util.LinkedList;

public class SnakeGameImpl implements SnakeGame{

    private int moveCounter = 0;
    private Snake snake;

    //private Direction currDirection;
    //private long currTimeStamp = -1;
    //private long prevTimeStamp;
    //private boolean isGameOver = false;
    SnakeGameImpl(){
        snake = new Snake();
    }

    /*public void setDirection(Direction direction) {
        this.currDirection = direction;
        this.prevTimeStamp = this.currTimeStamp;
        this.currTimeStamp = System.currentTimeMillis();
        if(isGameOver) return;
        update();
    }

    private void update(){
        if(prevTimeStamp != -1)
        {
            long diffInSecs = Duration.ofMillis(currTimeStamp-prevTimeStamp).getSeconds();
            System.out.println("Diff in secs" + diffInSecs);

            while(diffInSecs-- > 0){
                this.moveSnake(currDirection);
                if(this.isGameOver()){
                    System.out.println("GAMEOVER!!");
                    this.isGameOver = true;
                    return;
                }
            }
        }
    }*/
    public void moveSnake(Direction direction) {
        snake.move(direction);
        if(++moveCounter % 5 == 0){
            snake.grow();
            System.out.println("Snake grew");
        }
        System.out.println("Snake moved " + direction.toString());
    }

    public boolean isGameOver() {
            //if(isGameOver)
            //    return true;
            return snake.isHeadColliding();
    }

    private class Snake {
        private Deque<Cell> snake;
        private Cell removedCell;

        int BOARD_SIZE = 8;

        Snake(){
            snake = new LinkedList<>();
            snake.addLast(new Cell(10, 10));
            snake.addLast(new Cell(9, 10));
            snake.addLast(new Cell(8, 10));

            removedCell = null;
        }

        public void move(Direction direction){
            Cell head = snake.getFirst();
            Cell newHead = switch(direction){
                case UP -> new Cell(head.x, resolvedPos(head.y+1));
                case DOWN -> new Cell(head.x, resolvedPos(head.y-1));
                case RIGHT -> new Cell(resolvedPos(head.x+1), head.y);
                case LEFT -> new Cell(resolvedPos(head.x-1), head.y);
            };

            snake.addFirst(newHead);
            removedCell = snake.removeLast();
        }

        public boolean isHeadColliding(){
            Cell head = snake.getFirst();
            return snake.stream().skip(1).anyMatch(head::equals);
        }

        public void grow(){
            snake.addLast(removedCell);
        }

        private int resolvedPos(int pos){
            if(pos >= BOARD_SIZE)
                return 0;
            else if(pos < 0)
                return BOARD_SIZE -1;
            else
                return pos;
        }
    }

    record Cell(int x, int y){
    }
}
