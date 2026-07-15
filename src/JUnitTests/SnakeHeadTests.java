package JUnitTests;

import SnakeGame.Grid;
import SnakeGame.SnakeHead;
import FigurasGeo.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeHeadTests {

    private SnakeHead snakeHead;
    private Grid grid;
    private int tileSize = 20;

    @BeforeEach
    public void setUp() {
        grid = new Grid(400, 400, tileSize, false, false);
        snakeHead = new SnakeHead(new Ponto<>(20, 20), 1, grid, 1, 0, tileSize);
    }



    @Test
    public void testConstructorNullPoint() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SnakeHead(null, 1, grid, 1, 0, tileSize);
        });
        assertEquals("SnakeHead:vi", exception.getMessage());
    }



    @Test
    public void testCheckWallCollision() {
        snakeHead.move(10, 10); // Move outside the grid
        assertTrue(snakeHead.checkWallCollision());
    }


    @Test
    public void testSetAndGetVelocityX() {
        snakeHead.setVelocityX(2);
        assertEquals(2, snakeHead.getVelocityX());
    }

    @Test
    public void testSetAndGetVelocityY() {
        snakeHead.setVelocityY(2);
        assertEquals(2, snakeHead.getVelocityY());
    }

    @Test
    public void testSetIsSnakeHead() {
        snakeHead.setIsSnakeHead(true);
        for (Ponto<Integer> p : snakeHead.getSnakeHeadPontos()) {
            assertTrue(grid.getTilePixel(p).isSnakeHead());
        }
        snakeHead.setIsSnakeHead(false);
        for (Ponto<Integer> p : snakeHead.getSnakeHeadPontos()) {
            assertFalse(grid.getTilePixel(p).isSnakeHead());
        }
    }


}
