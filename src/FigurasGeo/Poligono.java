package FigurasGeo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a polygon with a list of vertices and edges.
 * A polygon is a plane figure that is described by a finite number of straight line segments connected to form a closed polygonal chain or polygonal circuit.
 * The solid plane region, the bounding circuit, or the two together, may be called a polygon.
 *
 * @author a65873
 * @version 2.0
 * @inv The polygon must not have intersecting edges, there cannot be 3 consecutive collinear points, and a polygon is formed by at least 3 points.
 */
public class Poligono implements IFigurasGeometricas {
    protected List<Ponto<Integer>> vertices; // List of vertices of the polygon
    protected List<SegmentoReta> arestas; // List of edges of the polygon
    private int angle; // Angle of rotation
    private int cX; // Center X of the polygon
    private int cY; // Center Y of the polygon

    /**
     * Constructs a polygon with a list of points.
     *
     * @param p List of points
     */
    public Poligono(List<Ponto<Integer>> p) {
        int size = p.size();
        List<SegmentoReta> sRetas = new ArrayList<>();
        this.vertices = new ArrayList<>(size);
        this.arestas = new ArrayList<>(size / 2 + 1);

        for (int i = 0; i < size - 1; i++) {
            sRetas.add(new SegmentoReta(p.get(i), p.get(i + 1)));
        }
        sRetas.add(new SegmentoReta(p.get(p.size() - 1), p.get(0)));

        validaPoligono(p, sRetas);
        arestas.addAll(sRetas);
        vertices.addAll(p);
    }

    /**
     * Constructs a polygon with a string of points.
     * The string should represent the coordinates of the points that form the polygon.
     * The format of the string should be "n x1 y1 x2 y2 ... xn yn", where n is the number of points and xi, yi are the coordinates of the i-th point.
     *
     * @param s The string representing the points of the polygon.
     */
    public Poligono(String s) {
        this(processString(s));
    }

    /**
     * Constructs a polygon with a single point.
     *
     * @param p The point
     */
    public Poligono(Ponto<Integer> p) {
        this.vertices = new ArrayList<>();
        this.vertices.add(p);
    }

    /**
     * Processes a string representing the points of a polygon and returns a list of Ponto objects.
     * The format of the string should be "n x1 y1 x2 y2 ... xn yn", where n is the number of points and xi, yi are the coordinates of the i-th point.
     *
     * @param s The string representing the points of the polygon.
     * @return A list of Ponto objects representing the points of the polygon.
     */
    private static List<Ponto<Integer>> processString(String s) {
        String[] pontosArr = s.split(" ");
        int numPontos = Integer.parseInt(pontosArr[0]);
        List<Ponto<Integer>> pontos = new ArrayList<>();
        for (int i = 1; i < numPontos * 2; i += 2) {
            int x = Integer.parseInt(pontosArr[i]);
            int y = Integer.parseInt(pontosArr[i + 1]);
            pontos.add(new Ponto<>(x, y));
        }
        return pontos;
    }

    /**
     * Calculates the perimeter of the polygon.
     *
     * @return The calculated perimeter of the polygon
     */
    @Override
    public double calcPerimeter() {
        double perimeter = 0;
        for (SegmentoReta r : arestas) {
            perimeter += r.tamanho;
        }
        return perimeter;
    }

    /**
     * Checks if the list has a size greater than 2.
     *
     * @param p List of points
     */
    private void validaTamanho(List<Ponto<Integer>> p) {
        if (p.size() < 3) {
            throw new IllegalArgumentException("Poligono: A polygon must have at least 3 points.");
        }
    }

    /**
     * Receives 3 points and checks their collinearity.
     *
     * @param p1 First point
     * @param p2 Second point
     * @param p3 Third point
     * @return True if the points are collinear, false otherwise
     */
    private boolean isColinear(Ponto<Integer> p1, Ponto<Integer> p2, Ponto<Integer> p3) {
        Reta r = new Reta(p1, p2);
        return r.isColinear(p3);
    }

    /**
     * Checks if there are 3 points within the list with collinear coordinates.
     *
     * @param p List of points.
     */
    private void validaConsecutividade(List<Ponto<Integer>> p) {
        int size = p.size();
        for (int i = 0; i < size; i++) {
            Ponto<Integer> p1 = p.get(i);
            Ponto<Integer> p2 = p.get((i + 1) % size);
            Ponto<Integer> p3 = p.get((i + 2) % size);
            if (isColinear(p1, p2, p3)) {
                throw new IllegalArgumentException("Poligono: No three consecutive points can be collinear.");
            }
        }
    }

    /**
     * Checks if there is overlap/collision of edges.
     *
     * @param s List of line segments referring to the edges of the polygon
     */
    private void validaArestas(List<SegmentoReta> s) {
        int size = s.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (s.get(i).interseta(s.get(j))) {
                    throw new IllegalArgumentException("Poligono: Edges must not intersect.");
                }
            }
        }
    }

    /**
     * Validates the polygon.
     *
     * @param p List of points
     * @param s List of line segments
     */
    private void validaPoligono(List<Ponto<Integer>> p, List<SegmentoReta> s) {
        validaTamanho(p);
        validaConsecutividade(p);
        validaArestas(s);
    }

    /**
     * Checks if there is a collision with another polygon.
     *
     * @param p The polygon to check for collision.
     * @return True if there is a collision, false otherwise
     */
    @Override
    public boolean checkColision(Poligono p) {
        for (SegmentoReta s1 : this.arestas) {
            for (SegmentoReta s2 : p.arestas) {
                if (s1.interseta(s2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the polygon.
     *
     * @return A string representation of the polygon.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Poligono with ").append(vertices.size()).append(" vertices: [");
        for (int i = 0; i < vertices.size(); i++) {
            Ponto<Integer> p = vertices.get(i);
            sb.append("(").append(p.getX()).append(",").append(p.getY()).append(")");
            if (i < vertices.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Returns the vertices of the polygon.
     *
     * @return A list of points representing the vertices of the polygon.
     */
    public List<Ponto<Integer>> getVertices() {
        return vertices;
    }

    /**
     * Rotates the polygon by a given angle around a specified center point.
     *
     * @param angle  The angle to rotate the polygon.
     * @param center The center point around which to rotate the polygon.
     */
    public void rotatePoligono(int angle, Ponto<Double> center) {
        for (Ponto<Integer> ponto : vertices) {
            ponto.rotate(angle, center);
        }
    }

    /**
     * Calculates and returns the center point of the polygon.
     * The center point is calculated as the average of the x and y coordinates of all vertices of the polygon.
     *
     * @return A Ponto object representing the center point of the polygon.
     */
    public Ponto<Double> getCenterPoint() {
        double x = 0;
        double y = 0;
        for (Ponto<Integer> ponto : vertices) {
            x += ponto.getX();
            y += ponto.getY();
        }
        x = x / vertices.size();
        y = y / vertices.size();
        return new Ponto<>(x, y);
    }

    /**
     * Moves the polygon by a given distance along the x and y axes.
     *
     * @param dx The distance to move the polygon along the x-axis.
     * @param dy The distance to move the polygon along the y-axis.
     * @return The moved polygon.
     */
    public Poligono movePoligono(double dx, double dy) {
        for (Ponto<Integer> ponto : vertices) {
            ponto.move(dx, dy);
        }
        return new Poligono(vertices);
    }

    /**
     * Moves the center of the polygon to the given coordinates.
     *
     * @param centerX The new x-coordinate of the polygon's center.
     * @param centerY The new y-coordinate of the polygon's center.
     * @return The moved polygon.
     */
    public Poligono moveCenter(double centerX, double centerY) {
        Ponto<Double> center = getCenterPoint();
        double dx = (centerX - center.getX());
        double dy = (centerY - center.getY());
        return movePoligono(dx, dy);
    }

    /**
     * Sets the angle of the polygon.
     *
     * @param angle The angle to set.
     */
    @Override
    public void setAngle(int angle) {
        this.angle = angle;
    }

    /**
     * Sets the central X coordinate of the polygon.
     *
     * @param cX The central X coordinate to set.
     */
    @Override
    public void setcX(int cX) {
        this.cX = cX;
    }

    /**
     * Sets the central Y coordinate of the polygon.
     *
     * @param cY The central Y coordinate to set.
     */
    @Override
    public void setcY(int cY) {
        this.cY = cY;
    }

    /**
     * Sets the change in the X coordinate of the polygon.
     *
     * @param dx The change in the X coordinate to set.
     */
    @Override
    public void setDx(int dx) {
        this.cX += dx;
    }

    /**
     * Sets the change in the Y coordinate of the polygon.
     *
     * @param dy The change in the Y coordinate to set.
     */
    @Override
    public void setDy(int dy) {
        this.cY += dy;
    }

    /**
     * Gets the angle of the polygon.
     *
     * @return The angle of the polygon.
     */
    @Override
    public int getAngle() {
        return angle;
    }

    /**
     * Gets the central X coordinate of the polygon.
     *
     * @return The central X coordinate of the polygon.
     */
    @Override
    public int getcX() {
        return cX;
    }

    /**
     * Gets the central Y coordinate of the polygon.
     *
     * @return The central Y coordinate of the polygon.
     */
    @Override
    public int getcY() {
        return cY;
    }

    /**
     * Gets the change in the X coordinate of the polygon.
     *
     * @return The change in the X coordinate of the polygon.
     */
    @Override
    public int getDx() {
        return cX;
    }

    /**
     * Gets the change in the Y coordinate of the polygon.
     *
     * @return The change in the Y coordinate of the polygon.
     */
    @Override
    public int getDy() {
        return cY;
    }
}
