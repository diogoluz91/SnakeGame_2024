package JUnitTests;

import FigurasGeo.Circulo;
import FigurasGeo.Ponto;
import FigurasGeo.Poligono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CirculoTests {

    private Circulo circulo;
    private Ponto<Integer> center;

    @BeforeEach
    public void setUp() {
        center = new Ponto<>(5, 5);
        circulo = new Circulo(center, 3);
    }

    @Test
    public void testConstructorWithCenterAndRadius() {
        assertEquals(center, circulo.getCenter());
        assertEquals(3, circulo.getRadius(), 0.001);
    }



    @Test
    public void testGetRadius() {
        assertEquals(3, circulo.getRadius(), 0.001);
    }

    @Test
    public void testGetCenter() {
        assertEquals(center, circulo.getCenter());
    }

    @Test
    public void testCheckColisionWithPoligono() {
        Poligono poligono = new Poligono("4 0 0 5 0 5 5 0 5");
        assertFalse(circulo.checkColision(poligono));
    }

}