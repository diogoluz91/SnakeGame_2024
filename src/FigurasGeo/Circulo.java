package FigurasGeo;

import static SnakeGame.GameArenaTxt.grid;

/**
 * Represents a circle in a game grid.
 */
public class Circulo implements IFigurasGeometricas {

	private Ponto<Integer> center;
	private double radius;
	private int angle;
	private int cX;
	private int cY;
	private int dx;
	private int dy;

	/**
	 * Constructor for Circulo with a given center and radius.
	 *
	 * @param p The center point of the circle.
	 * @param r The radius of the circle.
	 */
	public Circulo(Ponto<Integer> p, int r) {
		this.center = p;
		this.radius = r;
		int x = p.getX() - r;
		int y = p.getY() - r;
	}


	/**
	 * Gets the radius of the circle.
	 *
	 * @return The radius of the circle.
	 */
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Gets the center point of the circle.
	 *
	 * @return The center point of the circle.
	 */
	public Ponto<Integer> getCenter() {
		return this.center;
	}

	/**
	 * Checks if this circle collides with another geometric figure.
	 *
	 * @param c The geometric figure to check collision with.
	 * @return True if there is a collision, false otherwise.
	 */
	public boolean checkColision(IFigurasGeometricas c) {
		return false;
	}

	@Override
	public double calcPerimeter() {
		return 2 * Math.PI * this.radius;
	}

	@Override
	public boolean checkColision(Poligono p) {
		return false;
	}

	/**
	 * Gets the center point of the circle.
	 *
	 * @return The center point of the circle.
	 */
	public Ponto<Integer> getCenterPoint() {
		return this.center;
	}

	@Override
	public void setAngle(int angle) {
		this.angle = angle;
	}

	@Override
	public void setcX(int cX) {
		this.cX = cX;
	}

	@Override
	public void setcY(int cY) {
		this.cY = cY;
	}

	@Override
	public void setDx(int dx) {
		this.dx = dx;
	}

	@Override
	public void setDy(int dy) {
		this.dy = dy;
	}

	@Override
	public int getAngle() {
		return this.angle;
	}

	@Override
	public int getcX() {
		return this.cX;
	}

	@Override
	public int getcY() {
		return this.cY;
	}

	@Override
	public int getDx() {
		return this.dx;
	}

	@Override
	public int getDy() {
		return this.dy;
	}
}
