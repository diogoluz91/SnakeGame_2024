package FigurasGeo;

/**
 * Represents a line segment defined by two distinct points.
 * A line segment is a part of a line that is bounded by two distinct endpoints.
 * It has a slope calculated from the two endpoints.
 *
 * @version 2.0
 * @inv The line must have two distinct points.
 */
public class Reta {
    protected Ponto<Integer> p1;
    protected Ponto<Integer> p2;
    protected double inclinacao;

    /**
     * Constructs a line segment with two distinct points.
     *
     * @param ponto1 The first point of the line segment.
     * @param ponto2 The second point of the line segment.
     * @throws IllegalArgumentException if the two points are the same.
     */
    public Reta(Ponto<Integer> ponto1, Ponto<Integer> ponto2) {
        checkReta(ponto1, ponto2);
        this.p1 = new Ponto<>(ponto1);
        this.p2 = new Ponto<>(ponto2);
        this.inclinacao = (double) (ponto2.getY() - ponto1.getY()) / (ponto2.getX() - ponto1.getX());
    }

    /**
     * Checks if the two points are distinct.
     *
     * @param ponto1 The first point.
     * @param ponto2 The second point.
     * @throws IllegalArgumentException if the two points are the same.
     */
    private void checkReta(Ponto<Integer> ponto1, Ponto<Integer> ponto2) {
        if (ponto1.getX().equals(ponto2.getX()) && ponto1.getY().equals(ponto2.getY())) {
            throw new IllegalArgumentException("Reta: The two points must be distinct.");
        }
    }

    /**
     * Checks if a given point is collinear with this line segment.
     *
     * @param p The point to check.
     * @return True if the point is collinear with the line segment, false otherwise.
     */
    public boolean isColinear(Ponto<Integer> p) {
        return (p2.getY() - p1.getY()) * (p.getX() - p2.getX()) == (p.getY() - p2.getY()) * (p2.getX() - p1.getX());
    }
}
