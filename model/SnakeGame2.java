package task2.model;

import java.lang.Math;
import java.util.Arrays;

public class SnakeGame2 implements GameInterface {

	private static final int width  = 15;
    private static final int height = 10;
    private static final int maxPoint = 20;
    private static final int maxObs = 30;
    
    private int point = 0;

    private SnakeSegment[] snake;
    
    private int foodX;
    private int[] foodXs = new int[maxPoint];
    private int[] foodObtainedXs = new int[point];
    private int foodY;
    private int[] foodYs = new int[maxPoint];
    private int[] foodObtainedYs = new int[point];
    
    private int obsX;
    private int[] obsXs = new int[maxObs];
    private int obsY;
    private int[] obsYs = new int[maxObs];

    private enum GameState {
        RUNNING,
        WON,
        LOST
    };

    private GameState state;

    public SnakeGame2() {
        snake = new SnakeSegment[3];
        snake[0] = new SnakeSegment(width / 2, height / 2);
        snake[1] = new SnakeSegment((width / 2) + 1, height / 2);
        snake[2] = new SnakeSegment((width / 2) + 2, height / 2);
        spawnFood();
        state = GameState.RUNNING;
    }
    
    public int getPoint() {
    	return this.point;
    }
    
    public static int findIndex(int[] arr, int toFind) {
        if (arr == null) {
            return -1;
        }
        int len = arr.length;
        int i = 0;
        while (i < len) {
            if (arr[i] == toFind) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }
    public static int[] removeIntAtIndex(int[] arr, int index) {
        if (arr == null || index < 0
            || index >= arr.length) {
 
            return arr;
        }
        int[] anotherArray = new int[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            anotherArray[k++] = arr[i];
        }
        return anotherArray;
    }
    
    private boolean isSnakeSelfColiding() {
        for (SnakeSegment segment1 : snake) {
            for (SnakeSegment segment2 : snake) {
                if (segment1 != segment2 && segment1.getX() == segment2.getX() && segment1.getY() == segment2.getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void spawnFood() {
        foodX = (int) (Math.random() * width);
        foodY = (int) (Math.random() * height);
        for (int i = 0; i < this.snake.length; i++) {
        	if (foodX == this.snake[i].getX() && foodY == this.snake[i].getY()) {
        		spawnFood();
        	}
        }
    }
    private void spawnObs() {
    	obsX = (int) (Math.random() * width);
    	obsY = (int) (Math.random() * height);
    	if (obsX == this.foodX && obsY == this.foodY) {
    		spawnObs();
    	}
    	for (int i = 0; i < this.snake.length; i++) {
        	if (obsX == this.snake[i].getX() && obsY == this.snake[i].getY()) {
        		spawnFood();
        	}
        }
    }
    
    public void storeFood() {
    	for (int i = 0; i < maxPoint; i++) {
    		spawnFood();
    		this.foodXs[i] = foodX;
    		this.foodYs[i] = foodY;
    	}
    }
    public void storeObs() {
    	for (int i = 0; i < maxObs; i++) {
    		spawnObs();
    		this.obsXs[i] = obsX;
    		this.obsYs[i] = obsY;
    	}
    }

    private boolean isSnakeColiding() {
        if (isSnakeSelfColiding()) {
            return true;
        }
        SnakeSegment head = snake[0];
        if (head.getX() < 0 || head.getX() >= width || head.getY() < 0 || head.getY() >= height) {
            return true;
        }
        for (int i = 0; i < maxObs; i++) {
        	if (head.getX() == obsXs[i] && head.getY() == obsYs[i]) {
            	return true;
            }
        }
        return false;
    }
    
    private boolean canPickUp() {
    	SnakeSegment head = snake[0];
    	for (int i = 0; i < maxPoint - this.point; i++) {
    		if (head.getX() == foodXs[i] && head.getY() == foodYs[i]) {
    			return true;
    		}
    	}
    	return false;
    }

    private void moveSnake(Direction dir) {
    	SnakeSegment newHead = snake[0].getMoved(dir);
        for (int i = snake.length - 1; i > 0; i--) {
            snake[i] = snake[i - 1];
        }
        snake[0] = newHead;
    }

    private boolean isGameWon() {
        return this.point == maxPoint;
    }

    public void pressUpButton() {
        moveSnake(Direction.UP);
        if (isSnakeColiding()) {
            state = GameState.LOST;
        } else if (isGameWon()) {
            state = GameState.WON;
        }
    }

    public void pressDownButton() {
        moveSnake(Direction.DOWN);
        if (isSnakeColiding()) {
            state = GameState.LOST;
        } else if (isGameWon()) {
            state = GameState.WON;
        }
    }

    public void pressLeftButton() {
        moveSnake(Direction.LEFT);
        if (isSnakeColiding()) {
            state = GameState.LOST;
        } else if (isGameWon()) {
            state = GameState.WON;
        }
    }

    public void pressRightButton() {
        moveSnake(Direction.RIGHT);
        if (isSnakeColiding()) {
            state = GameState.LOST;
        } else if (isGameWon()) {
            state = GameState.WON;
        }
    }

    public void pressAButton() {
    	if (canPickUp()) {
    		this.point++;
    		for (int i = 0; i < point; i++) {
    			foodObtainedXs = Arrays.copyOf(foodObtainedXs, this.point);
				foodObtainedYs = Arrays.copyOf(foodObtainedXs, this.point);
    			foodObtainedXs[i] = this.snake[0].getX();
    			foodObtainedYs[i] = this.snake[0].getY();
    		}
    		foodXs = removeIntAtIndex(foodXs, findIndex(foodXs, this.snake[0].getX()));
    		foodYs = removeIntAtIndex(foodYs, findIndex(foodYs, this.snake[0].getY()));
    	}
    	if (isGameWon()) {
    		state = GameState.WON;
    	}
    }

    public void pressBButton() {
        return;
    }

    public String getGameState() {
        if (isRunning()) {
            char[][] grid = new char[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    grid[i][j] = ' ';
                }
            }
            for (int i = 0; i < this.point; i++) {
            	grid[foodObtainedXs[i]][foodObtainedYs[i]] = ' ';
            }
            for (SnakeSegment segment : snake) {
                grid[segment.getY()][segment.getX()] = 'O';
            }
            for (int i = 0; i < maxPoint - this.point; i++) {
                grid[foodYs[i]][foodXs[i]] = '$';
            }
            for (int i = 0; i < maxObs; i++) {
            	grid[obsYs[i]][obsXs[i]] = 'X';
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < width + 2; i++) {
                sb.append("#");
            }
            sb.append("\n");
            for (int i = 0; i < height; i++) {
                sb.append("#");
                for (int j = 0; j < width; j++) {
                    sb.append(grid[i][j]);
                }
                sb.append("#\n");
            }
            for (int i = 0; i < width + 2; i++) {
                sb.append("#");
            }
            return sb.toString();
        } else if (state == GameState.WON) {
            return "You won!";
        } else {
            return "You lost!";
        }
    }

    public String getUpLabel() {
        return "Up";
    }

    public String getDownLabel() {
        return "Down";
    }

    public String getLeftLabel() {
        return "Left";
    }

    public String getRightLabel() {
        return "Right";
    }

    public String getALabel() {
        return "A";
    }

    public String getBLabel() {
        return "B (not used)";
    }

    public boolean isRunning() {
        return state == GameState.RUNNING;
    }
}
