package SnakeGame;

import FigurasGeo.Poligono;
import FigurasGeo.Ponto;
import FigurasGeo.Quadrado;
import FigurasGeo.SegmentoReta;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tile in a game grid.
 */
public class Tile {
    private Ponto<Integer> pMin;
    private Ponto<Integer> pMax;
    private List<Ponto<Integer>> vertices = new ArrayList<>();
    private List<SegmentoReta> arestas = new ArrayList<>();

    /**
     * Indicates whether the tile contains food.
     */
    private boolean isFood = false;

    /**
     * Indicates whether the tile is occupied by the snake's head.
     */
    private boolean isSnakeHead = false;

    /**
     * Indicates whether the tile is occupied by the snake's body.
     */
    private boolean isSnakeBody = false;

    /**
     * Indicates whether the tile is occupied by an obstacle.
     */
    private boolean isObstacle = false;

    private int tileSize = GameArenaTxt.getTileSize();

    /**
     * Constructor for Tile with a given minimal position (xMin and yMin).
     *
     * @pre p != null
     * @pre p.getX() >= 0 && p.getY() >= 0
     * @param p the position of the tile
     */
    public Tile(Ponto<Integer> p) {
        if (p == null) {
            throw new IllegalArgumentException("Tile:vi");
        }
        if (p.getX() < 0 || p.getY() < 0) {
            throw new IllegalArgumentException("PontoTile:vi");
        }
        // Initialization of pMin and pMax
        pMin = new Ponto<>(p.getX(), p.getY());
        pMax = new Ponto<>(p.getX() + tileSize, p.getY() + tileSize);

        // Initialization of vertices (4 vertices)
        this.vertices.add(new Ponto<>(p.getX(), p.getY())); // p1
        this.vertices.add(new Ponto<>(p.getX() + tileSize, p.getY())); // p2
        this.vertices.add(new Ponto<>(p.getX() + tileSize, p.getY() + tileSize)); // p3
        this.vertices.add(new Ponto<>(p.getX(), p.getY() + tileSize)); // p4

        // Initialization of edges
        for (int i = 0; i < 3; i++) {
            this.arestas.add(new SegmentoReta(this.vertices.get(i), this.vertices.get(i + 1)));
        }
        this.arestas.add(new SegmentoReta(this.vertices.get(3), this.vertices.get(0)));
    }

    // SETTERS AND GETTERS

    /**
     * Returns the vertices of the tile.
     *
     * @return the vertices of the tile
     */
    public List<Ponto<Integer>> getVertices() {
        return this.vertices;
    }

    /**
     * Returns the edges of the tile.
     *
     * @return the edges of the tile
     */
    public List<SegmentoReta> getArestas() {
        return this.arestas;
    }

    /**
     * Returns the minimal x-coordinate of the tile.
     *
     * @return the minimal x-coordinate of the tile
     */
    public int getXMin() {
        return this.pMin.getX();
    }

    /**
     * Returns the minimal y-coordinate of the tile.
     *
     * @return the minimal y-coordinate of the tile
     */
    public int getYMin() {
        return this.pMin.getY();
    }

    /**
     * Returns the maximal x-coordinate of the tile.
     *
     * @return the maximal x-coordinate of the tile
     */
    public int getXMax() {
        return this.pMax.getX();
    }

    /**
     * Returns the maximal y-coordinate of the tile.
     *
     * @return the maximal y-coordinate of the tile
     */
    public int getYMax() {
        return this.pMax.getY();
    }

    /**
     * Sets the food status of the tile.
     *
     * @param b the food status to set
     */
    public void setIsFood(boolean b) {
        this.isFood = b;
    }

    /**
     * Sets the snake head occupation status of the tile.
     *
     * @param b the snake head occupation status to set
     */
    public void setIsSnakeHead(boolean b) {
        this.isSnakeHead = b;
    }

    /**
     * Sets the snake body occupation status of the tile.
     *
     * @param b the snake body occupation status to set
     */
    public void setIsSnakeBody(boolean b) {
        this.isSnakeBody = b;
    }

    /**
     * Sets the obstacle status of the tile.
     *
     * @param b the obstacle status to set
     */
    public void setIsObstacle(boolean b) {
        this.isObstacle = b;
    }

    // Check Boolean values

    /**
     * Returns the food status of the tile.
     *
     * @return true if the tile contains food, false otherwise
     */
    public boolean isFood() {
        return this.isFood;
    }

    /**
     * Returns the snake head occupation status of the tile.
     *
     * @return true if the tile is occupied by the snake's head, false otherwise
     */
    public boolean isSnakeHead() {
        return this.isSnakeHead;
    }

    /**
     * Returns the snake body occupation status of the tile.
     *
     * @return true if the tile is occupied by the snake's body, false otherwise
     */
    public boolean isSnakeBody() {
        return this.isSnakeBody;
    }

    /**
     * Returns the obstacle status of the tile.
     *
     * @return true if the tile is occupied by an obstacle, false otherwise
     */
    public boolean isObstacle() {
        return this.isObstacle;
    }

    // Functionality

    /**
     * Switches the properties of this tile with another tile.
     *
     * @param t the tile to switch properties with
     */
    public void switchTile(Tile t) {
        Tile tSwitch = new Tile(this.pMin);

        this.isFood = t.isFood;
        this.isObstacle = t.isObstacle;
        this.isSnakeHead = t.isSnakeHead;
        this.isSnakeBody = t.isSnakeBody;

        t.isFood = tSwitch.isFood;
        t.isObstacle = tSwitch.isObstacle;
        t.isSnakeHead = tSwitch.isSnakeHead;
        t.isSnakeBody = tSwitch.isSnakeBody;
    }

    /**
     * Checks if the tile collides with a given polygon.
     *
     * @param f the polygon to check collision with
     * @return true if the tile collides with the polygon, false otherwise
     */
    public boolean checkColision(Poligono f) {
        Quadrado q = new Quadrado(this.pMin);
        return q.checkColision(f);
    }
}
