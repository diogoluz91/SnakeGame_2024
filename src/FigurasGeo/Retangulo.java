package FigurasGeo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a rectangle, which is a specific type of polygon.
 * A rectangle is defined by exactly four points, with opposite sides of equal length and all angles equal to 90 degrees.
 * The class extends the Poligono class and adds additional functionality for handling rectangles.
 * It also overrides some methods from the Poligono class to provide rectangle-specific behavior.
 *
 * @author a65873
 * @version 2.0
 */
public class Retangulo extends Poligono {
    protected Ponto<Integer> pMin; // The point with the minimum x and y coordinates
    protected Ponto<Integer> pMax; // The point with the maximum x and y coordinates

    /**
     * Constructs a rectangle from a list of points.
     * The points should be provided in the order they are connected.
     * The constructor first checks if the provided list of points forms a rectangle.
     * If the points do not form a rectangle, the constructor prints an error message and terminates the program.
     * If the points form a rectangle, the constructor calculates the minimum and maximum x and y coordinates and creates two new points with these coordinates.
     * These points are assigned to pMin and pMax.
     *
     * @param p The list of points that form the rectangle.
     */
    public Retangulo(List<Ponto<Integer>> p) {
        super(p);
        checkRectangleSize(p);
        isRectangle(p);

        int xMax1 = Integer.MIN_VALUE;
        int yMax1 = Integer.MIN_VALUE;
        int xMin1 = Integer.MAX_VALUE;
        int yMin1 = Integer.MAX_VALUE;

        for (Ponto<Integer> p1 : p) {
            if (xMax1 < p1.getX()) {
                xMax1 = p1.getX();
            }
            if (yMax1 < p1.getY()) {
                yMax1 = p1.getY();
            }
            if (xMin1 > p1.getX()) {
                xMin1 = p1.getX();
            }
            if (yMin1 > p1.getY()) {
                yMin1 = p1.getY();
            }
        }

        pMin = new Ponto<Integer>(xMin1, yMin1);
        pMax = new Ponto<Integer>(xMax1, yMax1);
    }

    /**
     * Constructs a rectangle from a string of points.
     * The string should represent the coordinates of the points that form the rectangle.
     * The format of the string should be "x1 y1 x2 y2 x3 y3 x4 y4", where xi, yi are the coordinates of the i-th point.
     * The constructor processes the string and creates a new Retangulo object with the points represented in the string.
     *
     * @param s The string representing the points of the rectangle.
     */
    public Retangulo(String s) {
        super(processString(s));
        List<Ponto<Integer>> p = processString(s);
        checkRectangleSize(p);
        isRectangle(p);

        int xMax1 = Integer.MIN_VALUE;
        int yMax1 = Integer.MIN_VALUE;
        int xMin1 = Integer.MAX_VALUE;
        int yMin1 = Integer.MAX_VALUE;

        for (Ponto<Integer> p1 : p) {
            if (xMax1 < p1.getX()) {
                xMax1 = p1.getX();
            }
            if (yMax1 < p1.getY()) {
                yMax1 = p1.getY();
            }
            if (xMin1 > p1.getX()) {
                xMin1 = p1.getX();
            }
            if (yMin1 > p1.getY()) {
                yMin1 = p1.getY();
            }
        }

        pMin = new Ponto<Integer>(xMin1, yMin1);
        pMax = new Ponto<Integer>(xMax1, yMax1);
    }

    public Retangulo(Ponto<Integer> p) {
        super( p);
    }


    /**
     * This method checks if the provided list of points forms a rectangle.
     * A rectangle is defined by two pairs of points with equal distance.
     * If the distances are not equal, the method prints a message and terminates the program.
     *
     * @param p List of points that potentially form a rectangle
     */
    protected void isRectangle(List<Ponto<Integer>> p) {
        double dist1 = p.get(0).dist(p.get(1));
        double dist2 = p.get(1).dist(p.get(2));
        double dist3 = p.get(2).dist(p.get(3));
        double dist4 = p.get(3).dist(p.get(0));
        double diag1 = p.get(0).dist(p.get(2));
        double diag2 = p.get(1).dist(p.get(3));

        if (dist1 != dist3 || dist2 != dist4 || diag1 != diag2) {
            throw new IllegalStateException("Retangulo : vi");
        }
    }

    /**
     * This method checks if the provided list of points forms a rectangle.
     * A rectangle is defined by exactly 4 points.
     * If the number of points is not 4, the method prints a message and terminates the program.
     *
     * @param p List of points that potentially form a rectangle
     */
    public void checkRectangleSize(List<Ponto<Integer>> p) {
        if (p.size() != 4) {
            throw new IllegalStateException("Retangulo : vi");
        }
    }

    /**
     * This method checks if this rectangle collides with another rectangle.
     * Two rectangles collide if their x and y ranges overlap.
     * The method returns true if the rectangles collide and false otherwise.
     *
     * @param r The other rectangle to check for collision.
     * @return True if the rectangles collide, false otherwise.
     */
    public boolean checkColision(Retangulo r) {
        boolean colisaoX = this.pMin.getX() < r.pMax.getX() && this.pMax.getX() > r.pMin.getX();
        boolean colisaoY = this.pMin.getY() < r.pMax.getY() && this.pMax.getY() > r.pMin.getY();

        return colisaoX && colisaoY;
    }

    /**
     * This method returns the minimum x-coordinate of the rectangle.
     * The minimum x-coordinate is defined as the x-coordinate of the bottom-left point of the rectangle.
     *
     * @return The minimum x-coordinate of the rectangle
     */

    public int getXMin() {
        return this.pMin.getX();
    }

    /**
     * This method returns the minimum y-coordinate of the rectangle.
     * The minimum y-coordinate is defined as the y-coordinate of the bottom-left point of the rectangle.
     *
     * @return The minimum y-coordinate of the rectangle
     */

    public int getYMin() {
        return this.pMin.getY();
    }

    /**
     * This method returns the maximum x-coordinate of the rectangle.
     * The maximum x-coordinate is defined as the x-coordinate of the top-right point of the rectangle.
     *
     * @return The maximum x-coordinate of the rectangle
     */

    public int getXMax() {
        return this.pMax.getX();
    }

    /**
     * This method returns the maximum y-coordinate of the rectangle.
     * The maximum y-coordinate is defined as the y-coordinate of the top-right point of the rectangle.
     *
     * @return The maximum y-coordinate of the rectangle
     */

    public int getYMax() {
        return this.pMax.getY();
    }

    /**
     * This method processes a string representing the points of a rectangle and returns a list of Ponto objects.
     * The format of the string should be "x1 y1 x2 y2 x3 y3 x4 y4", where xi, yi are the coordinates of the i-th point.
     * The method splits the string into an array of strings, then loops through the array to create Ponto objects.
     * These objects are added to a list which is returned by the method.
     *
     * @param s The string representing the points of the rectangle.
     * @return A list of Ponto objects representing the points of the rectangle.
     */
    protected static List<Ponto<Integer>> processString(String s) {
        String[] pontosArr = s.split(" ");
        List<Ponto<Integer>> pontos = new ArrayList<>();
        for (int i = 0; i < pontosArr.length - 1; i += 2) {
            int x = Integer.parseInt(pontosArr[i]);
            int y = Integer.parseInt(pontosArr[i + 1]);
            pontos.add(new Ponto<Integer>(x, y));
        }
        return pontos;
    }

    /**
     * This method returns a string representation of the rectangle.
     * The string representation is in the format "Retangulo: [(x1,y1), (x2,y2), (x3,y3), (x4,y4)]",
     * where (xi, yi) are the coordinates of the i-th point of the rectangle.
     * The method uses a StringBuilder to build the string representation.
     * It loops through the list of vertices of the rectangle, and for each vertex, it appends its coordinates to the StringBuilder.
     * The coordinates are enclosed in parentheses and separated by a comma.
     * If the vertex is not the last one in the list, a comma and a space are appended after the coordinates.
     * Finally, the method returns the string representation of the rectangle.
     *
     * @return A string representation of the rectangle.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Retangulo: [");
        for (int i = 0; i < vertices.size(); i++) {
            Ponto<Integer> p = vertices.get(i);
            sb.append("(").append(p.getX()).append(",").append(p.getY()).append(")");
            if (i < vertices.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString().trim(); // remove the trailing space
    }

    /**
     * This method rotates the rectangle around a specified point by a given angle.
     *
     * @param angle
     * @param p
     */
    @Override
    public void rotatePoligono(int angle, Ponto<Double> p) {
        super.rotatePoligono(angle, p);
    }

    /**
     * This method moves the rectangle along the x and y axes by the specified distances.
     *
     * @param dx The distance to move the polygon along the x-axis.
     * @param dy The distance to move the polygon along the y-axis.
     * @return A new rectangle object with the vertices moved by the specified distances.
     */
    @Override
    public Retangulo movePoligono(double dx, double dy) {
        return (Retangulo) super.movePoligono(dx, dy);

    }


    public Retangulo move(List<Ponto<Integer>> vertices) {
        return new Retangulo(vertices);
    }

    /**
     * This method moves the center of the rectangle to the specified coordinates.
     *
     * @param centerX
     * @param centerY
     * @return
     */
    @Override
    public Retangulo moveCenter(double centerX, double centerY) {
        Ponto<Double> center = getCenterPoint();
        double dx = (centerX - center.getX());
        double dy = (centerY - center.getY());
        Retangulo r = movePoligono(dx, dy);
        return r;
    }


}