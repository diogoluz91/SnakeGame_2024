package JUnitTests;

import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Quadrado class.
 * Each method represents a test case for a specific functionality of the Quadrado class.
 */
class QuadradoTest {

    private List<Ponto<Integer>> pontosValidos;
    private List<Ponto<Integer>> pontosInvalidos;
    private Quadrado quadrado;

    @BeforeEach
    public void setUp() {
        pontosValidos = Arrays.asList(
                new Ponto<>(0, 0),
                new Ponto<>(2, 0),
                new Ponto<>(2, 2),
                new Ponto<>(0, 2)
        );

        pontosInvalidos = Arrays.asList(
                new Ponto<>(0, 0),
                new Ponto<>(4, 0),
                new Ponto<>(4, 3),
                new Ponto<>(0, 3)
        );

        quadrado = new Quadrado(pontosValidos);
    }


    @Test
    public void testConstructorWithInvalidPoints() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Quadrado(pontosInvalidos);
        });
        assertEquals("Quadrado:vi", exception.getMessage());
    }

    @Test
    public void testConstructorWithString() {
        Quadrado quadradoFromString = new Quadrado("0 0 2 0 2 2 0 2");
        assertEquals(4, quadradoFromString.getVertices().size());
    }

    @Test
    public void testGetXMin() {
        assertEquals(0, quadrado.getXMin());
    }

    @Test
    public void testGetYMin() {
        assertEquals(0, quadrado.getYMin());
    }

    @Test
    public void testGetXMax() {
        assertEquals(2, quadrado.getXMax());
    }

    @Test
    public void testGetYMax() {
        assertEquals(2, quadrado.getYMax());
    }

    @Test
    public void testCheckColision() {
        List<Ponto<Integer>> otherPoints = Arrays.asList(
                new Ponto<>(1, 1),
                new Ponto<>(3, 1),
                new Ponto<>(3, 3),
                new Ponto<>(1, 3)
        );
        Quadrado otherQuadrado = new Quadrado(otherPoints);
        assertTrue(quadrado.checkColision(otherQuadrado));

        List<Ponto<Integer>> noCollisionPoints = Arrays.asList(
                new Ponto<>(3, 3),
                new Ponto<>(5, 3),
                new Ponto<>(5, 5),
                new Ponto<>(3, 5)
        );
        Quadrado noCollisionQuadrado = new Quadrado(noCollisionPoints);
        assertFalse(quadrado.checkColision(noCollisionQuadrado));
    }

    @Test
    public void testToString() {
        String expectedString = "Quadrado: [(0,0), (2,0), (2,2), (0,2)]";
        assertEquals(expectedString, quadrado.toString());
    }


    /**
     * This test case checks if the isQuadrado method works correctly with a list of points that form a square.
     * It verifies if the method does not throw an exception.
     */
    @Test
    public void isQuadrado_withEqualDistances_doesNotTerminate() {
        List<Ponto<Integer>> pontos = List.of(new Ponto<Integer>(1, 1), new Ponto<Integer>(1, 2), new Ponto<Integer>(2, 2), new Ponto<Integer>(2, 1));
        Quadrado quadrado = new Quadrado(pontos);
        assertDoesNotThrow(() -> quadrado.isQuadrado(pontos));
    }

    /**
     * This test case checks if the isQuadrado method works correctly with a list of points that do not form a square.
     * It verifies if the method throws an exception.
     */
    @Test
    public void isQuadradoSidesEquals() {
        List<Ponto<Integer>> pontos = List.of(new Ponto<Integer>(1, 1), new Ponto<Integer>(1, 3), new Ponto<Integer>(3, 3), new Ponto<Integer>(3, 1));
        assertEquals(2, pontos.get(0).dist(pontos.get(1)));
        assertEquals(2, pontos.get(1).dist(pontos.get(2)));
        assertEquals(2, pontos.get(2).dist(pontos.get(3)));
        assertEquals(2, pontos.get(3).dist(pontos.get(0)));
    }


}