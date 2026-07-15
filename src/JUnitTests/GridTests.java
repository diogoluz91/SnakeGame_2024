package JUnitTests;

import SnakeGame.*;
import FigurasGeo.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GridTests {

    private Grid grid;
    private int frameWidth = 400;
    private int frameHeight = 400;
    private int tileSize = 20;

    @BeforeEach
    public void setUp() {
        grid = new Grid(frameWidth, frameHeight, tileSize, false, false);
    }

    @Test
    public void testConstructorWithValidDimensions() {
        assertNotNull(grid);
        assertEquals(frameWidth, grid.getWidth());
        assertEquals(frameHeight, grid.getHeight());
    }

    @Test
    public void testConstructorWithInvalidDimensions() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Grid(-1, -1, tileSize, false, false);
        });
        assertEquals("Grid:vi", exception.getMessage());
    }

    @Test
    public void testInitializeGrid() {
        List<List<Tile>> gridTiles = grid.getGrid();
        assertEquals(frameWidth / tileSize, gridTiles.size());
        assertEquals(frameHeight / tileSize, gridTiles.get(0).size());
    }

    @Test
    public void testGetTileIndex() {
        Ponto<Integer> point = new Ponto<>(5, 5);
        Tile tile = grid.getTileIndex(point);
        assertNotNull(tile);
        assertEquals(point.getX(), tile.getXMin());
        assertEquals(point.getY(), tile.getYMin());
    }

    @Test
    public void testGetTilePixel() {
        Ponto<Integer> point = new Ponto<>(100, 100);
        Tile tile = grid.getTilePixel(point);
        assertNotNull(tile);
        assertEquals(point.getX() / tileSize, tile.getXMin());
        assertEquals(point.getY() / tileSize, tile.getYMin());
    }


    @Test
    public void testDrawText() {
        grid.drawText();
        // This test ensures no exceptions are thrown during the execution
    }

    @Test
    public void testGameOver() {
        Score score = new Score("Player", 100);
        // Test game over scenario
        new Thread(() -> {
            try {
                grid.gameOver( score);
            } catch (Exception e) {
                fail("Game over threw an exception: " + e.getMessage());
            }
        }).start();
    }


}
