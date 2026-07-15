package JUnitTests;

import FigurasGeo.Ponto;
import FigurasGeo.Poligono;
import FigurasGeo.SegmentoReta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PoligonoTest {

    private List<Ponto<Integer>> pontosValidos;
    private List<Ponto<Integer>> pontosInvalidos;
    private Poligono poligono;

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

        poligono = new Poligono(pontosValidos);
    }

    @Test
    public void testConstructorWithValidPoints() {
        assertEquals(4, poligono.getVertices().size());
        assertEquals(pontosValidos, poligono.getVertices());
    }




    @Test
    public void testConstructorWithString() {
        Poligono poligonoFromString = new Poligono("4 0 0 4 0 4 3 0 3");
        assertEquals(4, poligonoFromString.getVertices().size());
    }

    @Test
    public void testCalcPerimeter() {
        double expectedPerimeter = 14.0; // 4 + 3 + 4 + 3
        assertEquals(expectedPerimeter, poligono.calcPerimeter(), 0.001);
    }

    @Test
    public void testCheckColision() {
        List<Ponto<Integer>> otherPoints = Arrays.asList(
                new Ponto<>(2, 1),
                new Ponto<>(6, 1),
                new Ponto<>(6, 4),
                new Ponto<>(2, 4)
        );
        Poligono otherPoligono = new Poligono(otherPoints);
        assertTrue(poligono.checkColision(otherPoligono));

        List<Ponto<Integer>> noCollisionPoints = Arrays.asList(
                new Ponto<>(5, 5),
                new Ponto<>(7, 5),
                new Ponto<>(7, 7),
                new Ponto<>(5, 7)
        );
        Poligono noCollisionPoligono = new Poligono(noCollisionPoints);
        assertFalse(poligono.checkColision(noCollisionPoligono));
    }

    @Test
    public void testGetCenterPoint() {
        Ponto<Double> center = poligono.getCenterPoint();
        assertEquals(2.0, center.getX(), 0.001);
        assertEquals(1.5, center.getY(), 0.001);
    }

    @Test
    public void testToString() {
        String expectedString = "Poligono de 4 vertices: [(0,0), (4,0), (4,3), (0,3)]";
        assertEquals(expectedString, poligono.toString());
    }
}