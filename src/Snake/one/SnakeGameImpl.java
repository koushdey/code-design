package com.atlassian;

//import java.time.Duration;        //LEVEL 1 & 2
import java.util.Deque;
import java.util.LinkedList;

public class SnakeGameImpl implements SnakeGame{

    private int moveCounter = 0;
    private Snake snake;

    //private Direction currDirection;      //LEVEL 1 & 2
    //private long currTimeStamp = -1;      //LEVEL 1 & 2
    //private long prevTimeStamp;           //LEVEL 1 & 2
    //private boolean isGameOver = false;   //LEVEL 1 & 2
    SnakeGameImpl(){
        snake = new Snake();
    }

    /*
    public void setDirection(Direction direction) {       //LEVEL 1 & 2
        this.currDirection = direction;
        if(isGameOver) return;
        update();
    }

    private void update(){
        this.prevTs = this.currTs;
        this.currTs = System.currentTimeMillis();
        if(prevTs != -1){
            long diffInSecs = Duration.ofMillis(currTs - prevTs).getSeconds();

            while(diffInSecs-- > 0){
                this.moveSnake(currDirection);
                if(this.isGameOver()){
                    this.isGameOver = true;
                    return;
                }
            }
        }
    }
    */

    public void moveSnake(Direction direction) {
        snake.move(direction);
        if(++moveCounter % 5 == 0){
            snake.grow();
            System.out.println("Snake grew");
        }
        System.out.println("Snake moved " + direction.toString());
    }

    public boolean isGameOver() {
            //if(isGameOver)        //LEVEL 1 & 2
            //    return true;
            update();
            return snake.isHeadColliding();
    }

    /*
    public String render() {        //LEVEL 2
        return !isGameOver() ? snake.render() : "GAME OVER!!";
    }
    */

    private class Snake {
        private Deque<Cell> snake;
        private Cell removedCell;

        int BOARD_SIZE = 20;
        //int BOARD_SIZE = 8; //LEVEL 1 & 2

        Snake(){
            snake = new LinkedList<>();
            snake.addLast(new Cell(10, 10));
            snake.addLast(new Cell(9, 10));
            snake.addLast(new Cell(8, 10));
            //snake.addLast(new Cell(4, 4));    //LEVEL 1 & 2
            //snake.addLast(new Cell(3, 4));    //LEVEL 1 & 2
            //snake.addLast(new Cell(2, 4));    //LEVEL 1 & 2

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

        /*
        public String render() {      //LEVEL 2
            StringBuilder sb = new StringBuilder();
    
            sb.append("><> ");
    
            for(Cell cell : snake){
                sb.append(cell.toString()+", ");
            }
            sb.setLength(sb.length()-2);
            sb.append(" -->");
    
            return sb.toString();
        }
        */
    }

    record Cell(int x, int y){
    }
}
