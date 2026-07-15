package SnakeGame;

import FigurasGeo.Ponto;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the grid for the Snake game. The grid consists of tiles that can be occupied by the snake, food, or obstacles.
 */
public class Grid {
    private List<List<Tile>> grid;
    private int tileSize;
    private int frameWidth;
    private int frameHeight;
    private boolean isFoodCircle;
    private boolean isFullRender;

    /**
     * Constructs a Grid object with the specified dimensions, tile size, and rendering options.
     *
     * @param frameWidth_   The width of the game frame.
     * @param frameHeight_  The height of the game frame.
     * @param tileSize_     The size of each tile.
     * @param isFoodCircle_ Specifies if the food is a circle.
     * @param isFullRender_ Specifies if full rendering is enabled.
     * @throws IllegalArgumentException if the frame width or height is non-positive.
     */
    public Grid(int frameWidth_, int frameHeight_, int tileSize_, boolean isFoodCircle_, boolean isFullRender_) {
        if (frameWidth_ <= 0 || frameHeight_ <= 0) {
            throw new IllegalArgumentException("Grid: The frame width and height must be positive.");
        }
        this.frameWidth = frameWidth_;
        this.frameHeight = frameHeight_;
        this.tileSize = tileSize_;
        this.isFoodCircle = isFoodCircle_;
        this.isFullRender = isFullRender_;
        initializeGrid();
    }

    /**
     * Initializes the grid with empty tiles.
     */
    private void initializeGrid() {
        int rows = frameHeight / tileSize;
        int cols = frameWidth / tileSize;

        grid = new ArrayList<>(cols);
        for (int i = 0; i < cols; i++) {
            grid.add(new ArrayList<>(rows));
            for (int j = 0; j < rows; j++) {
                grid.get(i).add(new Tile(new Ponto<>(i, j)));
                setTileIndex(false, false, false, false, i, j);
            }
        }
    }

    /**
     * Returns the tile at the specified index position.
     *
     * @param p The point with x and y coordinates as index positions.
     * @return The tile at the specified index position.
     */
    public Tile getTileIndex(Ponto<Integer> p) {
        return grid.get(p.getX()).get(p.getY());
    }

    /**
     * Returns the tile at the specified pixel position.
     *
     * @param p The point with x and y coordinates as pixel positions.
     * @return The tile at the specified pixel position.
     */
    public Tile getTilePixel(Ponto<Integer> p) {
        return grid.get(p.getX() / tileSize).get(p.getY() / tileSize);
    }

    /**
     * Sets the properties of the tile at the specified index position.
     *
     * @param isSnakeHead Specifies if the tile is part of the snake's head.
     * @param isSnakeBody Specifies if the tile is part of the snake's body.
     * @param isFood      Specifies if the tile contains food.
     * @param isObstacle  Specifies if the tile contains an obstacle.
     * @param x           The x index of the tile.
     * @param y           The y index of the tile.
     */
    private void setTileIndex(boolean isSnakeHead, boolean isSnakeBody, boolean isFood, boolean isObstacle, int x, int y) {
        grid.get(x).get(y).setIsSnakeHead(isSnakeHead);
        grid.get(x).get(y).setIsSnakeBody(isSnakeBody);
        grid.get(x).get(y).setIsFood(isFood);
        grid.get(x).get(y).setIsObstacle(isObstacle);
    }

    /**
     * Draws the grid in text form.
     */
    public void drawText() {
        for (int j = 0; j < grid.get(0).size(); j++) {
            for (List<Tile> row : grid) {
                Tile tile = row.get(j);
                if (tile.isSnakeHead()) {
                    System.out.print(" H"); // Snake head
                } else if (tile.isSnakeBody()) {
                    System.out.print(" T"); // Snake body
                } else if (tile.isObstacle()) {
                    System.out.print(" O"); // Obstacle
                } else if (tile.isFood()) {
                    System.out.print(" F"); // Food
                } else {
                    System.out.print(" ."); // Empty
                }
            }
            System.out.print(" \n");
        }
        System.out.print(" \n\n");
    }

    /**
     * Returns the grid.
     *
     * @return The grid.
     */
    public List<List<Tile>> getGrid() {
        return grid;
    }

    /**
     * Ends the game and displays the game over screen.
     *
     * @param s The score at the end of the game.
     */
    public void gameOver(Score s) {
        GameOver gameOver = new GameOver(s);
        System.exit(0);
    }

    /**
     * Returns the width of the grid.
     *
     * @return The width of the grid.
     */
    public int getWidth() {
        return this.frameWidth;
    }

    /**
     * Returns the height of the grid.
     *
     * @return The height of the grid.
     */
    public int getHeight() {
        return this.frameHeight;
    }

    /**
     * Draws the grid with the specified graphics context.
     *
     * @param g The graphics context to draw on.
     */
    public void draw(Graphics g) {
        for (int i = 0; i < this.frameWidth / tileSize; i++) {
            for (int j = 0; j < this.frameHeight / tileSize; j++) {
                Tile t = getTileIndex(new Ponto<>(i, j));

                if (isFullRender) {

                    if (t.isSnakeHead()) {
                        g.setColor(Color.GREEN);
                        g.fill3DRect(i * tileSize, j * tileSize, tileSize, tileSize, true);
                    } else if (t.isSnakeBody()) {
                        g.setColor(Color.YELLOW);
                        g.fill3DRect(i * tileSize, j * tileSize, tileSize, tileSize, true);
                    } else if (t.isObstacle()) {
                        g.setColor(Color.PINK);
                        g.fill3DRect(i * tileSize, j * tileSize, tileSize, tileSize, false);
                    } else if (t.isFood()) {
                        g.setColor(Color.RED);
                        if (isFoodCircle) {
                            g.fillOval(i * tileSize, j * tileSize, tileSize, tileSize);
                        } else {
                            g.fill3DRect(i * tileSize, j * tileSize, tileSize, tileSize, true);
                        }
                    }
                } else {
                    if (t.isSnakeHead()) {
                        g.setColor(Color.GREEN);
                        g.draw3DRect(i * tileSize, j * tileSize, tileSize, tileSize,true);
                    } else if (t.isSnakeBody()) {
                        g.setColor(Color.YELLOW);
                        g.draw3DRect(i * tileSize, j * tileSize, tileSize, tileSize,true);
                    } else if (t.isObstacle()) {
                        g.setColor(Color.PINK);
                        g.draw3DRect(i * tileSize, j * tileSize, tileSize, tileSize,false);
                    } else if (t.isFood()) {
                        g.setColor(Color.RED);
                        if (isFoodCircle) {
                            g.drawOval(i * tileSize, j * tileSize, tileSize, tileSize);
                        } else {
                            g.draw3DRect(i * tileSize, j * tileSize, tileSize, tileSize,true);
                        }
                    }
                }
            }
        }
    }
}
