package JUnitTests;

import SnakeGame.Food;
import SnakeGame.Grid;
import FigurasGeo.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTests {

    private Food food;
    private Grid grid;
    private int tileSize = 20;
    private int frameWidth = 400;
    private int frameHeight = 400;

    @BeforeEach
    public void setUp() {
        grid = new Grid(frameWidth, frameHeight, tileSize, false, false);
        food = new Food(false, grid, tileSize, frameWidth, frameHeight);
    }

    @Test
    public void testPlaceFoodRandomInt() {
        food.placeFoodRandomInt(frameWidth, frameHeight);
        Ponto<Integer> foodPosition = food.getFoodPonto();
        assertNotNull(foodPosition);
        assertTrue(foodPosition.getX() >= 0 && foodPosition.getX() < frameWidth);
        assertTrue(foodPosition.getY() >= 0 && foodPosition.getY() < frameHeight);
    }

    @Test
    public void testIsCircle() {
        assertFalse(food.isCircle());
    }
}