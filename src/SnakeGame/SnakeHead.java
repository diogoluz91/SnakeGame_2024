package SnakeGame;

import FigurasGeo.Ponto;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the head of the snake in the game.
 * The head is composed of multiple segments, each represented by a Ponto object.
 */
public class SnakeHead {
    private List<Ponto<Integer>> snakeHead;
    private int velocityX;
    private int velocityY;
    private Grid grid;
    private int snakeSize;
    private int snakeJump;
    private int tileSize;

    /**
     * Constructs a SnakeHead object with the specified initial parameters.
     *
     * @param p The initial position of the snake's head.
     * @param size The size of the snake's head (1 tile, 4 tiles, or 9 tiles).
     * @param grid_ The grid on which the snake moves.
     * @param velocityX_ The initial velocity in the x direction.
     * @param velocityY_ The initial velocity in the y direction.
     * @param tileSize_ The size of each tile.
     * @throws IllegalArgumentException if the initial position is null.
     */
    public SnakeHead(Ponto<Integer> p, int size, Grid grid_, int velocityX_, int velocityY_, int tileSize_) throws IllegalArgumentException {
        if (p == null) throw new IllegalArgumentException("SnakeHead:vi");
        this.snakeHead = new ArrayList<>();
        this.grid = grid_;
        this.velocityX = velocityX_;
        this.velocityY = velocityY_;
        this.tileSize = tileSize_;
        this.snakeSize = size;

        if (snakeSize == 1) {
            snakeJump = 1;
        } else if (snakeSize == 4) {
            snakeJump = 2;
        } else if (snakeSize == 9) {
            snakeJump = 3;
        }

        initializeHead(p);
    }

    /**
     * Initializes the head of the snake.
     *
     * @param p The initial position of the snake's head.
     */
    private void initializeHead(Ponto<Integer> p) {
        int x = p.getX();
        int y = p.getY();
        switch (snakeSize) {
            case 1:
                snakeHead.add(new Ponto<>(x, y));
                break;
            case 4:
                snakeHead.add(new Ponto<>(x, y)); // top left
                snakeHead.add(new Ponto<>(x + tileSize, y)); // top right
                snakeHead.add(new Ponto<>(x, y + tileSize)); // bottom left
                snakeHead.add(new Ponto<>(x + tileSize, y + tileSize)); // bottom right
                break;
            case 9:
                snakeHead.add(new Ponto<>(x, y)); // top left
                snakeHead.add(new Ponto<>(x + tileSize, y)); // top center
                snakeHead.add(new Ponto<>(x + tileSize * 2, y)); // top right
                snakeHead.add(new Ponto<>(x, y + tileSize)); // center left
                snakeHead.add(new Ponto<>(x + tileSize, y + tileSize)); // center center
                snakeHead.add(new Ponto<>(x + tileSize * 2, y + tileSize)); // center right
                snakeHead.add(new Ponto<>(x, y + tileSize * 2)); // bottom left
                snakeHead.add(new Ponto<>(x + tileSize, y + tileSize * 2)); // bottom center
                snakeHead.add(new Ponto<>(x + tileSize * 2, y + tileSize * 2)); // bottom right
                break;
        }
        setIsSnakeHead(true);
    }

    /**
     * Moves the snake head in the specified direction.
     *
     * @param velocityX The velocity in the x direction.
     * @param velocityY The velocity in the y direction.
     */
    public void move(int velocityX, int velocityY) {
        List<Ponto<Integer>> newPositions = new ArrayList<>();
        setVelocityX(velocityX);
        setVelocityY(velocityY);

        for (Ponto<Integer> p : snakeHead) {
            int newX = p.getX() + this.velocityX * tileSize * snakeJump;
            int newY = p.getY() + this.velocityY * tileSize * snakeJump;
            Ponto<Integer> p1 = new Ponto<>(newX, newY);
            newPositions.add(p1);
        }
        setIsSnakeHead(false);
        snakeHead.clear();
        snakeHead.addAll(newPositions);
        setIsSnakeHead(true);
    }

    /**
     * Draws the snake head on the screen.
     *
     * @param g The graphics context to draw on.
     */
    public void draw(Graphics g) {
        for (Ponto<Integer> p : snakeHead) {
            int x = p.getX();
            int y = p.getY();
            g.setColor(Color.GREEN);
            g.drawRect(x, y, tileSize, tileSize);
        }
    }

    /**
     * Draws and fills the snake head (implementation needed).
     */
    public void drawAndFill() {
        // Implementation goes here
    }

    /**
     * Updates the state of the snake head (implementation needed).
     */
    public void update() {
        // Implementation goes here
    }

    /**
     * Returns the velocity in the x direction.
     *
     * @return The velocity in the x direction.
     */
    public int getVelocityX() {
        return this.velocityX;
    }

    /**
     * Returns the velocity in the y direction.
     *
     * @return The velocity in the y direction.
     */
    public int getVelocityY() {
        return this.velocityY;
    }

    /**
     * Sets the velocity in the x direction.
     *
     * @param vX The velocity in the x direction.
     */
    public void setVelocityX(int vX) {
        this.velocityX = vX;
    }

    /**
     * Sets the velocity in the y direction.
     *
     * @param vY The velocity in the y direction.
     */
    public void setVelocityY(int vY) {
        this.velocityY = vY;
    }

    /**
     * Sets whether the current tile is part of the snake head.
     *
     * @param isSnakehead The boolean value indicating if the tile is part of the snake head.
     */
    public void setIsSnakeHead(boolean isSnakehead) {
        for (Ponto<Integer> p : snakeHead) {
            grid.getTilePixel(p).setIsSnakeHead(isSnakehead);
        }
    }

    /**
     * Returns the list of points representing the snake head.
     *
     * @return The list of points representing the snake head.
     */
    public List<Ponto<Integer>> getSnakeHeadPontos() {
        return snakeHead;
    }

    /**
     * Prints the current state of the snake head.
     */
    public void printSnakeHead() {
        if (snakeHead != null) {
            System.out.print("Snake Head:[");
            for (Ponto<Integer> p : snakeHead) {
                System.out.print("(" + p.getX() + "," + p.getY() + ")");
            }
            System.out.println("]");
        }
    }

    /**
     * Checks if the snake has collided with the wall.
     *
     * @return true if the snake has collided with the wall, false otherwise.
     */
    public boolean checkWallCollision() {
        for (Ponto<Integer> p : snakeHead) {
            int x = p.getX();
            int y = p.getY();

            // Calculate the new position after moving
            int newX = x + velocityX * tileSize * snakeJump;
            int newY = y + velocityY * tileSize * snakeJump;

            // Check if the new position is out of bounds
            if (newX < 0 || newY < 0 || newX >= grid.getWidth() || newY >= grid.getHeight()) {
                System.out.print("You hit a Wall! GAME OVER!");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the snake has collided with an obstacle.
     *
     * @return true if the snake has collided with an obstacle, false otherwise.
     */
    public boolean checkObstacleCollision() {
        for (Ponto<Integer> p : snakeHead) {
            int x = p.getX() + velocityX * tileSize * snakeJump;
            int y = p.getY() + velocityY * tileSize * snakeJump;
            if (grid.getTilePixel(new Ponto<>(x, y)).isObstacle()) {
                return true;
            }
        }
        return false;
    }
}
