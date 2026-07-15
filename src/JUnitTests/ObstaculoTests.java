package JUnitTests;

import FigurasGeo.*;
import SnakeGame.Obstaculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObstaculoTests {

    private Obstaculo obstaculo;
    private Poligono poligono;
    private Poligono poligonoDuplicado;
    private List<Ponto<Integer>> pontos;

    @BeforeEach
    public void setUp() {
        obstaculo = new Obstaculo();
        pontos = Arrays.asList(
                new Ponto<>(0, 0),
                new Ponto<>(4, 0),
                new Ponto<>(4, 3),
                new Ponto<>(0, 3)
        );
        poligono = new Poligono(pontos);
        poligonoDuplicado = new Poligono(pontos);
    }

    @Test
    public void testAddObstaculo() {
        assertTrue(obstaculo.addObstaculo(poligono));
        assertEquals(1, obstaculo.getNumberOfObstaculos());
    }

    @Test
    public void testAddDuplicateObstaculo() {
        obstaculo.addObstaculo(poligono);
        assertFalse(obstaculo.addObstaculo(poligonoDuplicado));
        assertEquals(1, obstaculo.getNumberOfObstaculos());
    }

    @Test
    public void testGetNumberOfObstaculos() {
        obstaculo.addObstaculo(poligono);
        assertEquals(1, obstaculo.getNumberOfObstaculos());
    }

    @Test
    public void testIsInsidePoligono() {
        Ponto<Integer> insidePoint = new Ponto<>(2, 2);
        Ponto<Integer> outsidePoint = new Ponto<>(5, 5);
        assertTrue(obstaculo.isInsidePoligono(insidePoint, poligono));
        assertFalse(obstaculo.isInsidePoligono(outsidePoint, poligono));
    }
}