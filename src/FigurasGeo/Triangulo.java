package FigurasGeo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Triangle which is a subclass of Poligono.
 * It contains methods to validate if the given points form a triangle and if the triangle is right-angled.
 */
public class Triangulo extends Poligono {

    /**
     * Constructor for the Triangulo class.
     * It calls the superclass constructor and validation methods to ensure the points form a right-angled triangle.
     *
     * @param p List of points that form the triangle
     */
    public Triangulo(List<Ponto<Integer>> p) {
        super(p);
        has3Points(p);
        //isTriangle(p);

    }
    public Triangulo(String s) {

        super(processString(s));
        has3Points(this.vertices);
       // isTriangle(this.vertices);

    }
    private static List<Ponto<Integer>> processString(String s){
        String[] pontosArr = s.split(" ");
        List<Ponto<Integer>> pontos = new ArrayList<>();
        for (int i = 0; i < pontosArr.length - 1 ; i += 2) {
            int x = Integer.parseInt(pontosArr[i]);
            int y = Integer.parseInt(pontosArr[i + 1]);
            pontos.add(new Ponto<Integer>(x, y));
        }
        return pontos;
    }
    /**
     * This method checks if the given list of points forms a triangle.
     * It throws an exception if the number of points is not equal to 3.
     *
     * @param p List of points
     */
    private void has3Points(List<Ponto<Integer>> p) {
        if (p.size() != 3) {
            throw new IllegalArgumentException("Triangulo:vi");
        }
    }

    /**
     * This method checks if the given points form a right-angled triangle using the Pythagorean theorem.
     * It throws an exception if the points do not form a right-angled triangle.
     *
     * @param p List of points
     */
    private void isTriangle(List<Ponto<Integer>> p) {
        double side1 = p.get(0).dist(p.get(1));
        double side2 = p.get(1).dist(p.get(2));
        double side3 = p.get(0).dist(p.get(2));
        double hypotenuse = Math.max(side1, Math.max(side2, side3));
        double sideA = (hypotenuse == side1) ? side2 : side1;
        double sideB = (hypotenuse == side3) ? side2 : side3;

        if (Math.pow(hypotenuse, 2) != Math.pow(sideA, 2) + Math.pow(sideB, 2)) {
          throw new IllegalArgumentException("Triangulo:vi");
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Triangulo: [");
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
     *  This method rotates the triangle by the given angle around the given point.
     * @param angle
     * @param p
     */
    @Override
    public void rotatePoligono(int angle, Ponto<Double> p) {
        super.rotatePoligono(angle, p);
    }

    /**
     * This method moves the triangle along the x and y axes by the given distances.
     * @param dx The distance to move the polygon along the x-axis.
     * @param dy The distance to move the polygon along the y-axis.
     * @return
     */
    @Override
    public Triangulo movePoligono(double dx, double dy) {
        for (Ponto<Integer> ponto : vertices) {
            ponto.move(dx, dy);
        }
        Triangulo triangulo = new Triangulo(vertices);
        return triangulo;
    }

    /**
     * This method moves the triangle to the given center point.
     * @param centerX
     * @param centerY
     * @return
     */
    @Override
    public Triangulo moveCenter(double centerX, double centerY) {
        Ponto<Double> center = getCenterPoint();
        double dx = (centerX - center.getX());
        double dy = (centerY - center.getY());
        Triangulo t = movePoligono(dx, dy);
        return t;
    }
}