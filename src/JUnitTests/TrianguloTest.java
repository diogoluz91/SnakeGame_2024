package JUnitTests;

import FigurasGeo.Ponto;
import FigurasGeo.Triangulo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrianguloTest {

    private List<Ponto<Integer>> pontosValidos;
    private List<Ponto<Integer>> pontosInvalidos;
    private Triangulo triangulo;

    @BeforeEach
    public void setUp() {
        pontosValidos = Arrays.asList(
                new Ponto<>(0, 0),
                new Ponto<>(3, 0),
                new Ponto<>(0, 4)
        );

        pontosInvalidos = Arrays.asList(
                new Ponto<>(0, 0),
                new Ponto<>(4, 0),
                new Ponto<>(2, 2),
                new Ponto<>(0, 3)
        );

        triangulo = new Triangulo(pontosValidos);
    }

    @Test
    public void testConstructorWithValidPoints() {
        assertEquals(3, triangulo.getVertices().size());
        assertEquals(pontosValidos, triangulo.getVertices());
    }

    @Test
    public void testConstructorWithInvalidPoints() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Triangulo(pontosInvalidos);
        });
        assertEquals("Triangulo:vi", exception.getMessage());
    }

    @Test
    public void testConstructorWithString() {
        Triangulo trianguloFromString = new Triangulo("0 0 3 0 0 4");
        assertEquals(3, trianguloFromString.getVertices().size());
    }

    @Test
    public void testToString() {
        String expectedString = "Triangulo: [(0,0), (3,0), (0,4)]";
        assertEquals(expectedString, triangulo.toString());
    }
}