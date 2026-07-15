package SnakeGame;

import FigurasGeo.Ponto;

import java.util.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents the game arena for the Snake game. It includes the grid, snake, food, obstacles, and handles the game loop and rendering.
 */
public class GameArenaTxt extends Component {

    private static Grid g; // Grid
    private Obstaculo obst; // Obstacles
    private Snake snake; // Snake body and head
    private Food food;
    private boolean isFoodCircle;
    private boolean completeRendering;
    private boolean automaticInput;

    public static int frameWidth;
    public static int frameHeigth;
    private int snakeTileNumber;
    private int foodTileNumber;

    public static int tileSize = 20;
    private static Score score;

    /**
     * Constructs the elements that constitute the game arena (food, obstacles, snake, and game window).
     *
     * @param foodCircle_ Specifies if the food is a circle.
     * @param completeRendering Specifies if complete rendering is enabled.
     * @param automaticInput Specifies if automatic input is enabled.
     * @param frameW The width of the game frame.
     * @param frameH The height of the game frame.
     * @param snakeTileNumber_ The number of tiles for the snake.
     * @param foodTileNumber The number of tiles for the food.
     * @param obstaculo The obstacles in the game.
     */
    public GameArenaTxt(boolean foodCircle_, boolean completeRendering, boolean automaticInput, int frameW, int frameH, int snakeTileNumber_, int foodTileNumber, Obstaculo obstaculo) {
        this.isFoodCircle = foodCircle_;
        this.completeRendering = completeRendering;
        this.automaticInput = automaticInput;

        frameWidth = frameW;
        frameHeigth = frameH;
        this.snakeTileNumber = snakeTileNumber_;
        this.foodTileNumber = foodTileNumber;
        this.obst = obstaculo;

        // Initialize the Score
        score = new Score(null, 0);
        // Initialize the game Grid
        g = new Grid(frameWidth, frameHeigth, tileSize, isFoodCircle, false);
        // Initialize obstacles
        this.obst.placeObstaculo(g);

        // Initialize Food
        this.food = new Food(this.isFoodCircle, g, tileSize, frameWidth, frameHeigth);

        // Initialize Snake
        this.snake = new Snake(snakeTileNumber, new Ponto<>(5 * tileSize, 5 * tileSize), g, tileSize); // Fixed initialization

        draw();
        // Start game loop
        startGameLoop();
    }

    /**
     * Starts the game loop.
     */
    public void startGameLoop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Read the next character from the console
            char c = scanner.next().charAt(0);

            // Update the velocity based on the character read
            switch (c) {
                case 'w':
                    snake.setVelocityY(-1);
                    snake.setVelocityX(0);
                    break;
                case 's':
                    snake.setVelocityY(1);
                    snake.setVelocityX(0);
                    break;
                case 'a':
                    snake.setVelocityY(0);
                    snake.setVelocityX(-1);
                    break;
                case 'd':
                    snake.setVelocityY(0);
                    snake.setVelocityX(1);
                    break;
            }

            // Execute the next loop
            update();
            // Add delay for smoother execution (adjust as necessary)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Draws the objects within the game arena and filters what to draw based on a boolean value.
     */
    public void draw() {
        snake.printSnake();
        g.drawText();
        score.drawTxt(snake.head.getVelocityX(), snake.head.getVelocityY());
        update();
    }

    /**
     * Updates the drawings loop by loop.
     */
    public void update() {
        if (!automaticInput) {
            int x = food.getFoodPonto().getX();
            int y = food.getFoodPonto().getY();
            if (snake.checkWallCollision() || snake.checkObstacleCollision() || snake.checkBodyHeadCollision(new Ponto<>(x, y))) {
                g.gameOver(score);
            }
            System.out.println("Manual input enabled");
            snake.move(food);
            checkFoodCollision();
            snake.printSnake();
            g.drawText();
            score.drawTxt(snake.head.getVelocityX(), snake.head.getVelocityY());
        } else {
            System.out.println("Automatic input enabled");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    int x = food.getFoodPonto().getX();
                    int y = food.getFoodPonto().getY();
                    if (snake.checkWallCollision() || snake.checkObstacleCollision() || snake.checkBodyHeadCollision(new Ponto<>(x, y))) {
                        g.gameOver(score);
                    }
                    snake.autoMove(food);
                    checkFoodCollision();
                    snake.printSnake();
                    g.drawText();
                    score.drawTxt(snake.getVelocityX(), snake.getVelocityY());
                }
            }, 500, 1000);
        }
    }

    /**
     * Returns the tile size.
     *
     * @return tileSize
     */
    public static int getTileSize() {
        return tileSize;
    }

    /**
     * Returns the grid.
     *
     * @return g
     */
    public static Grid grid() {
        return g;
    }

    /**
     * Returns the score.
     *
     * @return score
     */
    public static Score getScore() {
        return score;
    }

    /**
     * Checks if the snake collided with the food.
     * If yes, the snake eats the food and the food is removed from the grid.
     */
    private void checkFoodCollision() {
        List<Ponto<Integer>> headPoints = snake.getSnakeHead().getSnakeHeadPontos();
        int x = food.getFoodPonto().getX();
        int y = food.getFoodPonto().getY();
        Ponto<Integer> foodPosition = new Ponto<>(x, y);

        for (Ponto<Integer> headPoint : headPoints) {
            System.out.println("Food position: " + foodPosition.getX() + "," + foodPosition.getY());
            System.out.println("Head position: " + headPoint.getX() + "," + headPoint.getY());
            if (foodPosition.isEquals(headPoint)) {
                System.out.println("Snake ate food!");

                snake.addBodyPart(new Ponto<>(x, y));
                // Remove the food from the grid
                food.placeFoodRandomInt(frameWidth, frameHeigth);

                // Update the score
                score.updateScore(1);
                break; // Exit the loop as soon as a collision is detected
            }
        }
    }
}
