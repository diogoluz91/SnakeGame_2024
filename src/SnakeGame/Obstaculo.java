package SnakeGame;

import FigurasGeo.IFigurasGeometricas;
import FigurasGeo.Poligono;
import FigurasGeo.Ponto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages obstacles in the Snake game. Obstacles can be static or dynamic and are represented by geometric figures.
 */
public class Obstaculo {

    private List<IFigurasGeometricas> obs;
    int level;

    /**
     * Constructs an empty Obstaculo object.
     */
    public Obstaculo() {
        this.obs = new ArrayList<>();
    }

    /**
     * Constructs Obstaculo objects with a given difficulty level.
     *
     * @param level The difficulty level of the obstacles (0, 1, or 2).
     * @param isDynamic Specifies if the obstacles are dynamic.
     */
    public Obstaculo(int level, boolean isDynamic) {
        this.level = level;
    }

    /**
     * Moves the obstacle.
     */
    public void move() {
        // Implementation goes here
    }

    /**
     * Rotates the obstacle.
     */
    public void rotate() {
        // Implementation goes here
    }

    /**
     * Returns the number of obstacles.
     *
     * @return The number of obstacles.
     */
    public int getNumberOfObstaculos() {
        return this.obs.size();
    }

    /**
     * Adds an obstacle to the game.
     *
     * @param figura The geometric figure representing the obstacle.
     * @return true if the obstacle was added, false otherwise.
     */
    public boolean addObstaculo(IFigurasGeometricas figura) {
        if (figura instanceof Poligono) {
            Poligono p = (Poligono) figura;
            List<Ponto<Integer>> sortedVertices = new ArrayList<>(p.getVertices());
            Collections.sort(sortedVertices);

            for (IFigurasGeometricas fig : this.obs) {
                if (fig instanceof Poligono) {
                    Poligono poligono = (Poligono) fig;
                    List<Ponto<Integer>> sortedPoligonoVertices = new ArrayList<>(poligono.getVertices());
                    Collections.sort(sortedPoligonoVertices);
                    int sizeEqual = 0;
                    for (Ponto<Integer> p1 : sortedVertices) {
                        for (Ponto<Integer> p2 : sortedPoligonoVertices) {
                            if (p1.isEquals(p2)) {
                                sizeEqual++;
                            }
                        }
                    }
                    if (sizeEqual == sortedVertices.size()) {
                        System.out.println("Duplicado");
                        return false;
                    }
                }
            }
            this.obs.add(p);
        }
        return true;
    }

    /**
     * Prints the obstacles.
     */
    public void printObstaculos() {
        for (IFigurasGeometricas obs : obs) {
            System.out.println(obs);
        }
    }

    /**
     * Places obstacles on the grid.
     *
     * @param grid The grid on which to place the obstacles.
     */
    public void placeObstaculo(Grid grid) {
        for (IFigurasGeometricas figura : obs) {
            if (figura instanceof Poligono) {
                Poligono poligono = (Poligono) figura;
                int minX = Integer.MAX_VALUE;
                int maxX = Integer.MIN_VALUE;
                int minY = Integer.MAX_VALUE;
                int maxY = Integer.MIN_VALUE;

                // Calculate the boundaries of the polygon
                for (Ponto<Integer> vertice : poligono.getVertices()) {
                    minX = Math.min(minX, vertice.getX());
                    maxX = Math.max(maxX, vertice.getX());
                    minY = Math.min(minY, vertice.getY());
                    maxY = Math.max(maxY, vertice.getY());
                }

                // Traverse each point within the polygon's boundaries
                for (int x = minX; x <= maxX; x++) {
                    for (int y = minY; y <= maxY; y++) {
                        Ponto<Integer> ponto = new Ponto<>(x, y);
                        // Check if the point is inside the polygon
                        if (isInsidePoligono(ponto, poligono)) {
                            // Convert coordinates to grid indices and activate the tile
                            int xGrid = x * 20;
                            int yGrid = y * 20;
                            grid.getTilePixel(new Ponto<>(xGrid, yGrid)).setIsObstacle(true); // Grid pixel coordinates
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks if a point is inside a polygon.
     *
     * @param p The point to check.
     * @param poligono The polygon to check against.
     * @return true if the point is inside the polygon, false otherwise.
     */
public boolean isInsidePoligono(Ponto<Integer> p, Poligono poligono) {
        int count = 0;
        List<Ponto<Integer>> vertices = poligono.getVertices();
        int numVertices = vertices.size();

        if (numVertices < 3) {
            return false; // A valid polygon needs at least 3 points.
        }

        // The last vertex is connected to the first
        Ponto<Integer> p1 = vertices.get(0);
        for (int i = 1; i <= numVertices; i++) {
            Ponto<Integer> p2 = vertices.get(i % numVertices);

            // Check if the point p is on the line segment p1 -> p2
            if (p.getY() > Math.min(p1.getY(), p2.getY()) && p.getY() <= Math.max(p1.getY(), p2.getY()) &&
                    p.getX() <= Math.max(p1.getX(), p2.getX()) && p1.getY() != p2.getY()) {

                // Calculate the intersection x of the line segment with the line y = p.getY()
                double xinters = (p.getY() - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY()) + p1.getX();
                // If the point p is to the left of the intersection point, then it crosses
                if (p1.getX() == p2.getX() || p.getX() <= xinters) {
                    count++;
                }
            }
            p1 = p2; // Next segment
        }

        // Odd -> inside, even -> outside
        return (count % 2 != 0);
    }
}
