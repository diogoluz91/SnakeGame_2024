package JUnitTests;

import SnakeGame.Tile;
import FigurasGeo.Ponto;
import FigurasGeo.Poligono;
import FigurasGeo.SegmentoReta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TileTests {

    private Tile tile;

    @BeforeEach
    public void setUp() {
        tile = new Tile(new Ponto<>(0, 0));
    }

    @Test
    public void testConstructorWithValidPoint() {
        Tile tile = new Tile(new Ponto<>(10, 10));
        assertNotNull(tile);
        assertEquals(10, tile.getXMin());
        assertEquals(10, tile.getYMin());
    }

    @Test
    public void testConstructorWithNullPoint() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Tile(null);
        });
        assertEquals("Tile:vi", exception.getMessage());
    }

    @Test
    public void testConstructorWithNegativePoint() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Tile(new Ponto<>(-1, -1));
        });
        assertEquals("Ponto:vi", exception.getMessage());
    }


    @Test
    public void testGetArestas() {
        List<SegmentoReta> arestas = tile.getArestas();
        assertEquals(4, arestas.size());
    }

    @Test
    public void testSetAndIsFood() {
        assertFalse(tile.isFood());
        tile.setIsFood(true);
        assertTrue(tile.isFood());
    }

    @Test
    public void testSetAndIsSnakeHead() {
        assertFalse(tile.isSnakeHead());
        tile.setIsSnakeHead(true);
        assertTrue(tile.isSnakeHead());
    }

    @Test
    public void testSetAndIsSnakeBody() {
        assertFalse(tile.isSnakeBody());
        tile.setIsSnakeBody(true);
        assertTrue(tile.isSnakeBody());
    }

    @Test
    public void testSetAndIsObstacle() {
        assertFalse(tile.isObstacle());
        tile.setIsObstacle(true);
        assertTrue(tile.isObstacle());
    }

    @Test
    public void testGetXMin() {
        assertEquals(0, tile.getXMin());
    }

    @Test
    public void testGetYMin() {
        assertEquals(0, tile.getYMin());
    }


    @Test
    public void testSwitchTile() {
        Tile otherTile = new Tile(new Ponto<>(20, 20));
        otherTile.setIsFood(true);
        otherTile.setIsObstacle(true);
        otherTile.setIsSnakeHead(true);
        otherTile.setIsSnakeBody(true);

        tile.switchTile(otherTile);

        assertTrue(tile.isFood());
        assertTrue(tile.isObstacle());
        assertTrue(tile.isSnakeHead());
        assertTrue(tile.isSnakeBody());

        assertFalse(otherTile.isFood());
        assertFalse(otherTile.isObstacle());
        assertFalse(otherTile.isSnakeHead());
        assertFalse(otherTile.isSnakeBody());
    }


}
