package JUnitTests;
import FigurasGeo.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PontoTest {

    private Ponto<Integer> pontoInt;
    private Ponto<Double> pontoDouble;

    @BeforeEach
    public void setUp() {
        pontoInt = new Ponto<>(3, 4);
        pontoDouble = new Ponto<>(3.0, 4.0);
    }

    @Test
    public void testGetX() {
        assertEquals(3, pontoInt.getX());
        assertEquals(3.0, pontoDouble.getX(), 0.001);
    }

    @Test
    public void testGetY() {
        assertEquals(4, pontoInt.getY());
        assertEquals(4.0, pontoDouble.getY(), 0.001);
    }

    @Test
    public void testSetX() {
        pontoInt.setX(5);
        assertEquals(5, pontoInt.getX());

        pontoDouble.setX(5.0);
        assertEquals(5.0, pontoDouble.getX(), 0.001);
    }

    @Test
    public void testSetY() {
        pontoInt.setY(6);
        assertEquals(6, pontoInt.getY());

        pontoDouble.setY(6.0);
        assertEquals(6.0, pontoDouble.getY(), 0.001);
    }

    @Test
    public void testCompareTo() {
        Ponto<Integer> otherPontoInt = new Ponto<>(3, 5);
        Ponto<Double> otherPontoDouble = new Ponto<>(3.0, 5.0);

        assertTrue(pontoInt.compareTo(otherPontoInt) < 0);
        assertTrue(pontoDouble.compareTo(otherPontoDouble) < 0);
    }

    @Test
    public void testIsEquals() {
        Ponto<Integer> samePontoInt = new Ponto<>(3, 4);
        Ponto<Double> samePontoDouble = new Ponto<>(3.0, 4.0);

        assertTrue(pontoInt.isEquals(samePontoInt));
        assertTrue(pontoDouble.isEquals(samePontoDouble));
    }

    @Test
    public void testRotate() {
        Ponto<Double> center = new Ponto<>(0.0, 0.0);
        pontoDouble.rotate(90, center);

        assertEquals(-4.0, pontoDouble.getX(), 0.001);
        assertEquals(3.0, pontoDouble.getY(), 0.001);
    }



    @Test
    public void testDist() {
        Ponto<Integer> otherPontoInt = new Ponto<>(0, 0);
        Ponto<Double> otherPontoDouble = new Ponto<>(0.0, 0.0);

        assertEquals(5.0, pontoInt.dist(otherPontoInt), 0.001);
        assertEquals(5.0, pontoDouble.dist(otherPontoDouble), 0.001);
    }

    @Test
    public void testInvalidPonto() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Ponto<>(-1, -1),
                "Expected constructor to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Ponto:vi"));
    }
}