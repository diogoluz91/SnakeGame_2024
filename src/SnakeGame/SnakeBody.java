package SnakeGame;

import FigurasGeo.Ponto;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the body of the snake in the game.
 * The body is composed of multiple segments, each represented by a Ponto object.
 */
public class SnakeBody {

    private List<Ponto<Integer>> snakeBody;
    private Grid grid;
    int tileSize;
    private int snakeSize;

    /**
     * Constructs a SnakeBody object with the specified initial parameters.
     *
     * @param snakeSize_ The size of the snake's body (either 1, 4, or 9).
     * @param grid_ The grid on which the snake moves.
     * @param initialPosition The initial position of the snake's body.
     * @param tileSize_ The size of each tile.
     */
    public SnakeBody(int snakeSize_, Grid grid_, Ponto<Integer> initialPosition, int tileSize_) {
        this.grid = grid_;
        this.snakeBody = new ArrayList<>();
        this.tileSize = tileSize_;
        this.snakeSize = snakeSize_;
        addBodyPart(initialPosition);
    }

    /**
     * Moves the snake body to a new position.
     *
     * @param newPosition The new position to move the snake body to.
     */
    public void move(Ponto<Integer> newPosition) {
        int x = newPosition.getX();
        int y = newPosition.getY();

        setIsSnakeBody(false);
        snakeBody.clear();
        switch (snakeSize) {
            case 1:
                snakeBody.add(new Ponto<>(x, y));
                break;
            case 4:
                snakeBody.add(new Ponto<>(x, y));
                snakeBody.add(new Ponto<>(x, y + tileSize));
                snakeBody.add(new Ponto<>(x + tileSize, y));
                snakeBody.add(new Ponto<>(x + tileSize, y + tileSize));
                break;

            case 9:
                snakeBody.add(new Ponto<>(x, y));
                snakeBody.add(new Ponto<>(x, y + tileSize));
                snakeBody.add(new Ponto<>(x, y + tileSize * 2));
                snakeBody.add(new Ponto<>(x + tileSize, y));
                snakeBody.add(new Ponto<>(x + tileSize, y + tileSize));
                snakeBody.add(new Ponto<>(x + tileSize, y + tileSize * 2));
                snakeBody.add(new Ponto<>(x + tileSize * 2, y));
                snakeBody.add(new Ponto<>(x + tileSize * 2, y + tileSize));
                snakeBody.add(new Ponto<>(x + tileSize * 2, y + tileSize * 2));
                break;
        }
        setIsSnakeBody(true);
    }

    /**
     * Sets the isSnakeBody property for each segment of the snake body.
     *
     * @param isSnakeBody The boolean value to set for the isSnakeBody property.
     */
    private void setIsSnakeBody(boolean isSnakeBody) {
        for (Ponto<Integer> p : snakeBody) {
            grid.getTilePixel(p).setIsSnakeBody(isSnakeBody);
        }
    }

    /**
     * Draws the snake body on the screen.
     *
     * @param g The graphics context to draw on.
     */
    public void draw(Graphics g) {
        for (Ponto<Integer> p : snakeBody) {
            int x = p.getX();
            int y = p.getY();
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, tileSize, tileSize);
        }
    }

    /**
     * Draws and fills the snake body (implementation needed).
     */
    public void drawAndFill() {
        // Implementation goes here
    }

    /**
     * Updates the state of the snake body (implementation needed).
     */
    public void update() {
        // Implementation goes here
    }

    /**
     * Adds a new body part to the snake.
     *
     * @param p The position of the new body part.
     * @pre p must belong to the grid and be empty.
     * @pre p must be adjacent to the snake's body.
     * @pre p must be in the direction of the snake's movement.
     * @post Adds a unit of body (1, 4, or 9 tiles) to the snake's body.
     */
    public void addBodyPart(Ponto<Integer> p) {
        int x = p.getX();
        int y = p.getY();

        switch (snakeSize) {
            case 1:
                snakeBody.add(new Ponto<>(x, y));
                break;
            case 4:
                snakeBody.add(new Ponto<>(x, y));
                snakeBody.add(new Ponto<>(x, y + tileSize));
                snakeBody.add(new Ponto<>(x + tileSize, y));
                snakeBody.add(new Ponto<>(x + tileSize, y + tileSize));
                break;

            case 9:
                snakeBody.add(new Ponto<>(x, y));
                snakeBody.add(new Ponto<>(x, y + tileSize));
                snakeBody.add(new Ponto<>(x, y + tileSize * 2));
                snakeBody.add(new Ponto<>(x + tileSize, y));
                snakeBody.add(new Ponto<>(x + tileSize, y + tileSize));
                snakeBody.add(new Ponto<>(x + tileSize, y + tileSize * 2));
                snakeBody.add(new Ponto<>(x + tileSize * 2, y));
                snakeBody.add(new Ponto<>(x + tileSize * 2, y + tileSize));
                snakeBody.add(new Ponto<>(x + tileSize * 2, y + tileSize * 2));
                break;
        }
    }

    /**
     * Returns the size of the snake body.
     *
     * @return The size of the snake body.
     */
    public int getSize() {
        if (this.snakeBody == null) return 0;
        return this.snakeBody.size();
    }

    /**
     * Prints the current state of the snake body.
     */
    public void printSnakeBody() {
        if (snakeBody == null) return;
        System.out.print("Snake Body:[");
        for (Ponto<Integer> p : snakeBody) {
            System.out.print("(" + p.getX() + "," + p.getY() + "),");
        }
        System.out.print("]\n");
    }

    /**
     * Returns the first point of the snake body.
     *
     * @return The first point of the snake body.
     */
    public Ponto<Integer> getFirstPonto() {
        if (snakeBody == null) return null;
        return snakeBody.get(0);
    }

    /**
     * Returns the list of points representing the snake body.
     *
     * @return The list of points representing the snake body.
     */
    public List<Ponto<Integer>> getSnakeBody() {
        return snakeBody;
    }
}
