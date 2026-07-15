package JUnitTests;

import FigurasGeo.Ponto;
import FigurasGeo.Retangulo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    private List<Ponto<Integer>> pontosValidos;
    private List<Ponto<Integer>> pontosInvalidos;
    private Retangulo retangulo;

    @BeforeEach
    public void setUp() {
        pontosValidos = Arrays.asList(
                new Ponto<>(0, 0),
                new Ponto<>(4, 0),
                new Ponto<>(4, 3),
                new Ponto<>(0, 3)
        );

        pontosInvalidos = Arrays.asList(
                new Ponto<>(0, 0),
                new Ponto<>(4, 0),
                new Ponto<>(2, 2),
                new Ponto<>(0, 3)
        );

        retangulo = new Retangulo(pontosValidos);
    }


    @Test
    public void testConstructorWithInvalidPoints() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            new Retangulo(pontosInvalidos);
        });
        assertEquals("Retangulo : vi", exception.getMessage());
    }

    @Test
    public void testConstructorWithString() {
        Retangulo retanguloFromString = new Retangulo("0 0 4 0 4 3 0 3");
        assertEquals(4, retanguloFromString.getVertices().size());
    }

    @Test
    public void testGetXMin() {
        assertEquals(0, retangulo.getXMin());
    }

    @Test
    public void testGetYMin() {
        assertEquals(0, retangulo.getYMin());
    }

    @Test
    public void testGetXMax() {
        assertEquals(4, retangulo.getXMax());
    }

    @Test
    public void testGetYMax() {
        assertEquals(3, retangulo.getYMax());
    }

    @Test
    public void testCheckColision() {
        List<Ponto<Integer>> otherPoints = Arrays.asList(
                new Ponto<>(2, 1),
                new Ponto<>(6, 1),
                new Ponto<>(6, 4),
                new Ponto<>(2, 4)
        );
        Retangulo otherRetangulo = new Retangulo(otherPoints);
        assertTrue(retangulo.checkColision(otherRetangulo));

        List<Ponto<Integer>> noCollisionPoints = Arrays.asList(
                new Ponto<>(5, 5),
                new Ponto<>(7, 5),
                new Ponto<>(7, 7),
                new Ponto<>(5, 7)
        );
        Retangulo noCollisionRetangulo = new Retangulo(noCollisionPoints);
        assertFalse(retangulo.checkColision(noCollisionRetangulo));
    }

    @Test
    public void testToString() {
        String expectedString = "Retangulo: [(0,0), (4,0), (4,3), (0,3)]";
        assertEquals(expectedString, retangulo.toString());
    }

}