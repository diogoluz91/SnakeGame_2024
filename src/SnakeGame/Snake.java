package SnakeGame;

import FigurasGeo.Ponto;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Represents the snake in the game.
 * The snake is composed of a head and a body.
 * The coordinates of the snake are integers and align with the grid.
 */
public class Snake {
    List<SnakeBody> body;
    SnakeHead head;
    private int velocityX;
    private int velocityY;
    private int snakeJump;
    private Grid grid;
    private int tileSize;

    /**
     * Constructs a Snake object with the specified initial parameters.
     *
     * @param snakeTiles The initial number of tiles for the snake.
     * @param p The initial position of the snake's head.
     * @param grid_ The grid on which the snake moves.
     * @param tileSize_ The size of each tile.
     */
    public Snake(int snakeTiles, Ponto<Integer> p, Grid grid_, int tileSize_) {
        this.velocityX = 1;
        this.velocityY = 0;
        this.snakeJump = snakeTiles;
        this.grid = grid_;
        this.tileSize = tileSize_;
        this.head = new SnakeHead(p, snakeJump, grid, this.velocityX, this.velocityY, tileSize);
        this.body = new ArrayList<>();
    }

    /**
     * Moves the snake.
     *
     * @param f The food object.
     */
    public void move(Food f) {
        if (body.size() > 0) {
            for (int i = body.size() - 1; i > 0; i--) {
                Ponto<Integer> previousSegmentPosition = body.get(i - 1).getFirstPonto();
                body.get(i).move(previousSegmentPosition);
            }
            body.get(0).move(head.getSnakeHeadPontos().get(0));
        }
        head.move(velocityX, velocityY);

        for (SnakeBody sB : body) {
            for (Ponto<Integer> p : sB.getSnakeBody()) {
                grid.getTilePixel(p).setIsSnakeBody(true);
            }
        }

        head.setIsSnakeHead(true);
    }

    /**
     * Checks if the snake has collided with the wall.
     *
     * @return true if the snake has collided with the wall, false otherwise.
     */
    public boolean checkWallCollision() {
        return head.checkWallCollision();
    }

    /**
     * Checks if the snake has collided with an obstacle.
     *
     * @return true if the snake has collided with an obstacle, false otherwise.
     */
    public boolean checkObstacleCollision() {
        if (getSnakeHead().checkObstacleCollision()) {
            System.out.print("You hit an obstacle! GAME OVER!");
        }
        return getSnakeHead().checkObstacleCollision();
    }

    /**
     * Checks if the snake's head has collided with its body.
     *
     * @param p The position to check.
     * @return true if the snake's head has collided with its body, false otherwise.
     */
    public boolean checkBodyHeadCollision(Ponto<Integer> p) {
        for (Ponto<Integer> pS : head.getSnakeHeadPontos()) {
            for (SnakeBody sB : body) {
                for (Ponto<Integer> pB : sB.getSnakeBody()) {
                    if (pS.isEquals(pB) && !pS.isEquals(p) && grid.getTilePixel(new Ponto<>(pS.getX(), pS.getY())).isSnakeBody()) {
                        System.out.print("You hit your body! GAME OVER!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Draws the snake on the screen.
     *
     * @param g The graphics context to draw on.
     */
    public void draw(Graphics g) {
        head.draw(g);
        for (SnakeBody part : body) {
            part.draw(g);
        }
    }

    /**
     * Updates the state of the snake.
     */
    public void update() {
        // Implementation goes here
    }

    /**
     * Draws and fills the snake.
     */
    public void drawAndFill() {
        // Implementation goes here
    }

    /**
     * Adds a new body part to the snake.
     *
     * @param p The position of the new body part.
     */
    public void addBodyPart(Ponto<Integer> p) {
        body.add(new SnakeBody(snakeJump, grid, p, tileSize));
    }

    /**
     * Returns the snake's head.
     *
     * @return The snake's head.
     */
    public SnakeHead getSnakeHead() {
        return this.head;
    }

    /**
     * Returns the snake's body.
     *
     * @return A list of the snake's body parts.
     */
    public List<SnakeBody> getSnakeBody() {
        return this.body;
    }

    /**
     * Prints the snake's current state.
     */
    public void printSnake() {
        head.printSnakeHead();
        for (SnakeBody part : body) {
            part.printSnakeBody();
        }
    }

    /**
     * Sets the snake's velocity in the x direction.
     *
     * @param vX The velocity in the x direction.
     */
    public void setVelocityX(int vX) {
        this.velocityX = vX;
        getSnakeHead().setVelocityX(vX);
    }

    /**
     * Sets the snake's velocity in the y direction.
     *
     * @param vY The velocity in the y direction.
     */
    public void setVelocityY(int vY) {
        this.velocityY = vY;
        getSnakeHead().setVelocityY(vY);
    }

    /**
     * Returns the snake's velocity in the x direction.
     *
     * @return The velocity in the x direction.
     */
    public int getVelocityX() {
        return this.velocityX;
    }

    /**
     * Returns the snake's velocity in the y direction.
     *
     * @return The velocity in the y direction.
     */
    public int getVelocityY() {
        return this.velocityY;
    }

    /**
     * Automatically moves the snake towards the food.
     *
     * @param f The food object.
     */
    public void autoMove(Food f) {
        Ponto<Integer> currentPosition = head.getSnakeHeadPontos().getFirst();
        Ponto<Integer> foodPosition = f.getFoodPonto();

        int dx = foodPosition.getX() - currentPosition.getX();
        int dy = foodPosition.getY() - currentPosition.getY();

        int[] direction = new int[2];
        boolean randomMove = false;

        // Determine the primary direction based on the difference in x and y coordinates
        if (Math.abs(dx) > Math.abs(dy)) {
            direction[0] = (dx > 0) ? 1 : -1;
            direction[1] = 0;
        } else {
            direction[0] = 0;
            direction[1] = (dy > 0) ? 1 : -1;
        }

        // Validate the primary direction
        if (isDirectionBlocked(currentPosition, direction) || (direction[0] == -getVelocityX() && direction[1] == -getVelocityY())) {
            if (direction[0] != 0) {
                direction[0] = 0;
                direction[1] = (dy > 0) ? 1 : -1;
            } else {
                direction[0] = (dx > 0) ? 1 : -1;
                direction[1] = 0;
            }

            if (isDirectionBlocked(currentPosition, direction) || (direction[0] == -getVelocityX() && direction[1] == -getVelocityY())) {
                int[][] directions = {
                        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
                };
                for (int[] dir : directions) {
                    if (!isDirectionBlocked(currentPosition, dir) && !(dir[0] == -getVelocityX() && dir[1] == -getVelocityY())) {
                        direction = dir;
                        break;
                    }
                }
            }
        }

        // Check if we are stuck (distance to food is increasing)
        int newDx = foodPosition.getX() - (currentPosition.getX() + direction[0] * tileSize);
        int newDy = foodPosition.getY() - (currentPosition.getY() + direction[1] * tileSize);

        if (Math.abs(newDx) > Math.abs(dx) || Math.abs(newDy) > Math.abs(dy)) {
            randomMove = true;
            for (int i = 0; i < 5; i++) {
                int[][] directions = {
                        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
                };
                for (int[] dir : directions) {
                    if (!isDirectionBlocked(currentPosition, dir)) {
                        direction = dir;
                        break;
                    }
                }
                setVelocityX(direction[0]);
                setVelocityY(direction[1]);
                move(f);
            }
        }

        if (!randomMove) {
            setVelocityX(direction[0]);
            setVelocityY(direction[1]);
            move(f);
        }
    }

    /**
     * Helper method to check if a direction is blocked by the body or an obstacle.
     *
     * @param currentPosition The current position of the snake's head.
     * @param direction The direction to check.
     * @return true if the direction is blocked, false otherwise.
     */
    private boolean isDirectionBlocked(Ponto<Integer> currentPosition, int[] direction) {
        int newX = currentPosition.getX() + direction[0] * tileSize;
        int newY = currentPosition.getY() + direction[1] * tileSize;
        Ponto<Integer> newPoint = new Ponto<>(newX, newY);

        return newX < 0 || newY < 0 || newX >= grid.getWidth() || newY >= grid.getHeight() ||
                grid.getTilePixel(newPoint).isSnakeBody() || grid.getTilePixel(newPoint).isObstacle();
    }

    /**
     * Checks if the snake has collided with the food.
     *
     * @param foodPosition The position of the food.
     * @return true if the snake has collided with the food, false otherwise.
     */
    public boolean checkFoodColision(Ponto<Integer> foodPosition) {
        int x = foodPosition.getX();
        int y = foodPosition.getY();
        for (Ponto<Integer> headPoint : head.getSnakeHeadPontos()) {
            if (foodPosition.isEquals(headPoint)) {
                addBodyPart(headPoint);
                return true;
            }
        }
        return false;
    }
}
