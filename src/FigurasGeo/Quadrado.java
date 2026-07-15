package FigurasGeo;


import java.util.List;

public class Quadrado extends Retangulo {
    /**
     * Constructor for the Rectangle class.
     * It first calls the superclass constructor with the list of points.
     * Then it checks if the provided list of points forms a rectangle by calling the checkRectangleSize and isRectangle methods.
     * It calculates the minimum and maximum x and y coordinates from the list of points.
     * Finally, it creates two new points with the minimum and maximum coordinates and assigns them to pMin and pMax.
     *
     * @param p List of points that potentially form a rectangle
     */
    public Quadrado(List<Ponto<Integer>> p) {
        super(p);
        isQuadrado(p);
    }

    public Quadrado(String s) {
        super(Retangulo.processString(s));
    }

    public Quadrado(Ponto<Integer> p) {
        super(p);
    }


    /**
     * This method checks if the provided list of points forms a square.
     * It iterates over the list of edges (segments) of the square.
     * If the length of any edge is not equal to the length of the first edge, it prints an error message and terminates the program.
     *
     * @param p List of points that potentially form a square
     */
    public void isQuadrado(List<Ponto<Integer>> p) {
        for (SegmentoReta s : this.arestas) {
            if (s.tamanho != this.arestas.get(0).tamanho) {
                throw new IllegalArgumentException("Quadrado:vi");
            }
        }
    }

    /**
     * Returns a string representation of the rectangle.
     * The string representation is in the format "Retangulo: [(x1,y1), (x2,y2), (x3,y3), (x4,y4)]",
     * where (xi, yi) are the coordinates of the i-th point of the rectangle.
     * The points are listed in the order they were provided when the rectangle was created.
     * The string representation does not include any trailing spaces.
     *
     * @return A string representation of the rectangle.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quadrado: [");
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
     * Rotates the polygon by the specified angle around the specified point.
     *
     * @param angle
     * @param p
     */
    @Override
    public void rotatePoligono(int angle, Ponto<Double>p) {
        super.rotatePoligono(angle, p);
    }

    /**
     * Moves the polygon by the specified distances along the x and y axes.
     *
     * @param dx The distance to move the polygon along the x-axis.
     * @param dy The distance to move the polygon along the y-axis.
     * @return
     */
    @Override
    public Quadrado movePoligono(double dx, double dy) {
        return (Quadrado) super.movePoligono(dx, dy);
    }

    @Override
    public Quadrado move(List<Ponto<Integer>> vertices) {
        return new Quadrado(vertices);
    }

    /**
     * Moves the center of the polygon to the specified coordinates.
     *
     * @param centerX
     * @param centerY
     * @return
     */
    @Override
    public Quadrado moveCenter(double centerX, double centerY) {
        Ponto<Double> center = getCenterPoint();
        double dx = (centerX - center.getX());
        double dy = (centerY - center.getY());
        Quadrado r = movePoligono(dx, dy);
        return r;
    }
    /**
     * Checks if the square collides with another square.
     *
     * @param q The square to check for collision.
     * @return true if the squares collide, false otherwise.
     */
    public boolean checkColision(Quadrado q){
        return super.checkColision(q);
    }
}
