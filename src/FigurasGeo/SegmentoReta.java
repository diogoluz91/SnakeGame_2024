package FigurasGeo;

/**
 * Represents a line segment defined by two distinct points.
 * A line segment is a part of a line that is bounded by two distinct endpoints.
 * It has a length calculated from the two endpoints.
 *
 * @version 2.0
 * @inv Non-intersection of edges, no three consecutive collinear points, and a polygon is formed by at least 3 points.
 * @see <a href="https://www.inf.pucrs.br/~pinho/CG/Aulas/OpenGL/Interseccao/CalcIntersec.html">Intersection Calculation</a>
 */
public class SegmentoReta extends Reta {
    protected double tamanho;

    /**
     * Constructs a line segment with two distinct points.
     *
     * @param ponto1 The first point of the line segment.
     * @param ponto2 The second point of the line segment.
     * @throws IllegalArgumentException if the two points are the same or if the length is not positive.
     */
    public SegmentoReta(Ponto<Integer> ponto1, Ponto<Integer> ponto2) {
        super(ponto1, ponto2);
        checkSegmentoReta(ponto1, ponto2);
        this.tamanho = calcDist();
    }

    /**
     * Given an object of the class SegmentoReta and an input line segment,
     * checks for collision between the two line segments using determinants.
     *
     * @param s The line segment to test for collision.
     * @return A boolean value, true if there is an intersection, false otherwise.
     */
    public boolean interseta(SegmentoReta s) {
        double x1 = this.p1.getX(); // k.x
        double y1 = this.p1.getY(); // k.y
        double x2 = this.p2.getX(); // l.x
        double y2 = this.p2.getY(); // l.y
        double x3 = s.p1.getX(); // m.x
        double y3 = s.p1.getY(); // m.y
        double x4 = s.p2.getX();  // n.x
        double y4 = s.p2.getY(); // n.y
        // Calculation of determinant values
        double det1 = (y2 - y1) * (x3 - x2) - (x2 - x1) * (y3 - y2);
        double det2 = (y2 - y1) * (x4 - x2) - (x2 - x1) * (y4 - y2);
        double det3 = (y4 - y3) * (x1 - x3) - (x4 - x3) * (y1 - y3);
        double det4 = (y4 - y3) * (x2 - x3) - (x4 - x3) * (y2 - y3);

        // Returns true if there is an intersection and false if not
        return (det1 * det2 < 0) && (det3 * det4 < 0);
    }

    /**
     * Calculates the distance between the points of the line segment.
     *
     * @return The length of a line segment with two defined points.
     */
    public double calcDist() {
        double dx = this.p1.getX() - this.p2.getX();
        double dy = this.p1.getY() - this.p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Given two points, checks that the distance between these two points is greater than zero.
     *
     * @param ponto1 The first point.
     * @param ponto2 The second point.
     * @throws IllegalArgumentException if the length is not positive.
     */
    private void checkSegmentoReta(Ponto<Integer> ponto1, Ponto<Integer> ponto2) {
        if (calcDist() <= 0) {
            throw new IllegalArgumentException("SegmentoReta: The length of the line segment must be positive.");
        }
    }
}
