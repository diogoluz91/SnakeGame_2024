package SnakeGame;

import FigurasGeo.Circulo;
import FigurasGeo.IFigurasGeometricas;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;

import java.awt.*;
import java.util.Random;

/**
 * Represents the food in the Snake game. The food can be either a circle or a square and is placed randomly on the grid.
 */
public class Food {

    private IFigurasGeometricas f;
    private Ponto<? extends Number> p;
    boolean isCircle;
    int tileSize;
    private Grid grid;

    /**
     * Constructor for Food.
     *
     * @param isFoodCircle Specifies if the food is a circle. If false, the food is a square.
     * @param grid_ The grid on which the food is placed.
     * @param tileSize_ The size of the tile.
     * @param frameWidth The width of the game frame.
     * @param frameHeight The height of the game frame.
     */
    public Food(boolean isFoodCircle, Grid grid_, int tileSize_, int frameWidth, int frameHeight) {
        this.isCircle = isFoodCircle;
        this.grid = grid_;
        this.tileSize = tileSize_;

        if (!isCircle) {
            placeFoodRandomInt(frameWidth, frameHeight);
            f = new Quadrado((Ponto<Integer>) p);
        } else {
            placeFoodRandomInt(frameWidth, frameHeight);
            f = new Circulo((Ponto<Integer>) p, this.tileSize / 2);
        }
    }

    /**
     * Draws the food on the grid.
     *
     * @param g The graphics context to draw on.
     */
    public void draw(Graphics g) {
        // Implementation goes here
    }

    /**
     * Draws and fills the food on the grid.
     */
    public void drawAndFill() {
        // Implementation goes here
    }

    /**
     * Places the food at a random position within the grid.
     *
     * @param frameWidth The width of the game frame.
     * @param frameHeight The height of the game frame.
     * @throws IllegalArgumentException if the tileSize is not positive or if the frame dimensions are invalid.
     */
    public void placeFoodRandomInt(int frameWidth, int frameHeight) {
        if (tileSize <= 0) {
            throw new IllegalArgumentException("tileSize must be positive");
        }

        Random random = new Random();
        int maxX = frameWidth / tileSize;
        int maxY = frameHeight / tileSize;

        if (maxX <= 0 || maxY <= 0) {
            throw new IllegalArgumentException("Invalid frame dimensions or tileSize");
        }

        Ponto<Integer> newFoodPosition;
        boolean isValidPosition;

        do {
            int x = random.nextInt(1, maxX - 1); // Ensuring x is in range [1, maxX - 1]
            int y = random.nextInt(1, maxY - 1); // Ensuring y is in range [1, maxY - 1]

            newFoodPosition = new Ponto<>(x * tileSize, y * tileSize);
            isValidPosition = true;

            // Check if the new position is an obstacle
            if (grid.getTilePixel(newFoodPosition).isObstacle()) {
                isValidPosition = false;
            }

            // Check if the new position is the body of the snake
            if (grid.getTilePixel(newFoodPosition).isSnakeBody()) {
                isValidPosition = false;
            }

            // Check if the new position is the head of the snake
            if (grid.getTilePixel(newFoodPosition).isSnakeHead()) {
                isValidPosition = false;
            }

        } while (!isValidPosition);

        // Update the position of the food
        if (p != null) {
            grid.getTilePixel((Ponto<Integer>) p).setIsFood(false);
        }

        this.p = newFoodPosition;
        grid.getTilePixel((Ponto<Integer>) p).setIsFood(true);
    }

    /**
     * Places the food at a random position within the grid using double precision.
     *
     * @return The new position of the food.
     */
    public Ponto<Double> placeFoodRandomDouble() {
        Random random = new Random();
        double x = random.nextDouble() * (double) GameArenaTxt.frameWidth / tileSize;
        double y = random.nextDouble() * (double) GameArenaTxt.frameHeigth / tileSize;

        this.p = new Ponto<>(x, y);
        return new Ponto<>(x, y);
    }

    /**
     * Gets the position of the food.
     *
     * @return The position of the food as a Ponto<Integer> object.
     */
    public Ponto<Integer> getFoodPonto() {
        int x = (int) p.getX();
        int y = (int) p.getY();
        return new Ponto<>(x, y);
    }

    /**
     * Checks if the food is a circle.
     *
     * @return True if the food is a circle, false otherwise.
     */
    public boolean isCircle() {
        return isCircle;
    }
}
