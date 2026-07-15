package FigurasGeo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a point in a 2D space with x and y coordinates.
 * The point is generic and can work with any type of Number.
 *
 * @param <T> The type of number used for the coordinates.
 */
public class Ponto<T extends Number> implements Comparable<Ponto<T>> {

    private T x;
    private T y;

    /**
     * Constructs a point with the specified coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @throws IllegalArgumentException if any coordinate is negative.
     */
    public Ponto(T x, T y) {
        check(x);
        check(y);
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a point by copying another point.
     *
     * @param p The point to copy.
     * @throws IllegalArgumentException if any coordinate is negative.
     */
    public Ponto(Ponto<T> p) {
        check(p.getX());
        check(p.getY());
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Checks if the coordinate is valid (non-negative).
     *
     * @param x The coordinate to check.
     * @throws IllegalArgumentException if the coordinate is negative.
     */
    protected void check(T x) {
        if (x.doubleValue() < 0) {
            throw new IllegalArgumentException("Ponto: Coordinate values must be non-negative.");
        }
    }

    /**
     * Gets the x coordinate.
     *
     * @return The x coordinate.
     */
    public T getX() {
        return this.x;
    }

    /**
     * Gets the y coordinate.
     *
     * @return The y coordinate.
     */
    public T getY() {
        return this.y;
    }

    /**
     * Compares this point to another point.
     *
     * @param o The other point.
     * @return A negative integer, zero, or a positive integer as this point is less than, equal to, or greater than the specified point.
     */
    @Override
    public int compareTo(Ponto<T> o) {
        int compareX = Double.compare(this.x.doubleValue(), o.getX().doubleValue());
        if (compareX == 0) {
            return Double.compare(this.y.doubleValue(), o.getY().doubleValue());
        } else {
            return compareX;
        }
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param p The other point.
     * @return True if the points are equal, false otherwise.
     */
    public boolean isEquals(Ponto<T> p) {
        return this.x.equals(p.x) && this.y.equals(p.y);
    }

    /**
     * Rotates the point around a specified center by a given angle.
     *
     * @param angle  The angle of rotation in degrees.
     * @param center The center point around which to rotate.
     * @throws ClassCastException if the type T cannot be cast to Double.
     */
    @SuppressWarnings("unchecked")
    public void rotate(int angle, Ponto<Double> center) {
        if (angle != 0) {
            BigDecimal x = BigDecimal.valueOf(this.x.doubleValue() - center.getX());
            BigDecimal y = BigDecimal.valueOf(this.y.doubleValue() - center.getY());
            BigDecimal rad = BigDecimal.valueOf(Math.toRadians(angle));

            BigDecimal cosRad = BigDecimal.valueOf(Math.cos(rad.doubleValue()));
            BigDecimal sinRad = BigDecimal.valueOf(Math.sin(rad.doubleValue()));

            BigDecimal newX = x.multiply(cosRad).subtract(y.multiply(sinRad)).add(BigDecimal.valueOf(center.getX()));
            BigDecimal newY = x.multiply(sinRad).add(y.multiply(cosRad)).add(BigDecimal.valueOf(center.getY()));

            this.x = (T) (Double) newX.doubleValue();
            this.y = (T) (Double) newY.doubleValue();
        }
    }

    /**
     * Moves the point by the specified distances along the x and y axes.
     *
     * @param dx The distance to move along the x-axis.
     * @param dy The distance to move along the y-axis.
     * @throws ClassCastException if the type T cannot be cast to Double.
     */
    @SuppressWarnings("unchecked")
    public void move(double dx, double dy) {
        this.x = (T) (Double) (this.x.doubleValue() + dx);
        this.y = (T) (Double) (this.y.doubleValue() + dy);
    }

    /**
     * Calculates the distance to another point.
     *
     * @param p The other point.
     * @return The distance to the other point.
     */
    public double dist(Ponto<? extends Number> p) {
        double dx = getX().doubleValue() - p.getX().doubleValue();
        double dy = getY().doubleValue() - p.getY().doubleValue();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Sets the x coordinate.
     *
     * @param x The x coordinate to set.
     */
    public void setX(T x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate.
     *
     * @param y The y coordinate to set.
     */
    public void setY(T y) {
        this.y = y;
    }
}
