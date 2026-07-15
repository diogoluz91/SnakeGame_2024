package JUnitTests;

import SnakeGame.Food;
import SnakeGame.Grid;
import SnakeGame.Snake;
import SnakeGame.SnakeBody;
import SnakeGame.SnakeHead;
import FigurasGeo.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTests {

    private Snake snake;
    private Grid grid;
    private int tileSize = 20;

    @BeforeEach
    public void setUp() {
        grid = new Grid(400, 400, tileSize, false, false);
        snake = new Snake(1, new Ponto<>(20, 20), grid, tileSize);
    }

    @Test
    public void testCheckFoodCollision() {
        // Add food to the grid
        Ponto<Integer> foodPosition = new Ponto<>(40, 20);
        Food food = new Food(false, grid, tileSize, 400, 400);
        food.placeFoodRandomInt(400, 400);

        // Move snake to food position
        snake.getSnakeHead().move(1, 0);
        assertTrue(snake.checkFoodColision(foodPosition));
    }


    @Test
    public void testSetAndGetVelocityX() {
        snake.setVelocityX(2);
        assertEquals(2, snake.getVelocityX());
    }

    @Test
    public void testSetAndGetVelocityY() {
        snake.setVelocityY(2);
        assertEquals(2, snake.getVelocityY());
    }


    @Test
    public void testAutoMove() {
        Food food = new Food(false, grid, tileSize, 400, 400);
        food.placeFoodRandomInt(400, 400);
        snake.autoMove(food);
        // This test is just to ensure no exceptions are thrown during autoMove.
    }
}
