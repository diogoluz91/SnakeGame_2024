package FigurasGeo;

/**
 * Interface that defines the operations that can be performed on geometric figures.
 */
public interface IFigurasGeometricas {

    /**
     * Calculates the perimeter of the geometric figure.
     *
     * @return The perimeter of the geometric figure.
     */
    double calcPerimeter();

    /**
     * Checks if there is a collision with a polygon.
     *
     * @param p The polygon to check for collision.
     * @return True if there is a collision, false otherwise.
     */
    boolean checkColision(Poligono p);

    /**
     * Sets the angle of the geometric figure.
     *
     * @param angle The angle to be set.
     */
    void setAngle(int angle);

    /**
     * Sets the central X coordinate of the geometric figure.
     *
     * @param cX The central X coordinate to be set.
     */
    void setcX(int cX);

    /**
     * Sets the central Y coordinate of the geometric figure.
     *
     * @param cY The central Y coordinate to be set.
     */
    void setcY(int cY);

    /**
     * Sets the change in the X coordinate of the geometric figure.
     *
     * @param dx The change in the X coordinate to be set.
     */
    void setDx(int dx);

    /**
     * Sets the change in the Y coordinate of the geometric figure.
     *
     * @param dy The change in the Y coordinate to be set.
     */
    void setDy(int dy);

    /**
     * Gets the angle of the geometric figure.
     *
     * @return The angle of the geometric figure.
     */
    int getAngle();

    /**
     * Gets the central X coordinate of the geometric figure.
     *
     * @return The central X coordinate of the geometric figure.
     */
    int getcX();

    /**
     * Gets the central Y coordinate of the geometric figure.
     *
     * @return The central Y coordinate of the geometric figure.
     */
    int getcY();

    /**
     * Gets the change in the X coordinate of the geometric figure.
     *
     * @return The change in the X coordinate of the geometric figure.
     */
    int getDx();

    /**
     * Gets the change in the Y coordinate of the geometric figure.
     *
     * @return The change in the Y coordinate of the geometric figure.
     */
    int getDy();
}
