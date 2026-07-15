package GUI;

import SnakeGame.*;

import javax.swing.*;
import java.awt.*;

/**
 * GameArenaView is a custom JPanel that represents the game arena for a Snake game.
 * It handles the drawing of the grid, snake, food, score, and obstacles.
 */
public class GameArenaView extends JPanel {
    private Grid grid;
    private Snake snake;
    private Food food;
    private Score score;
    private Obstaculo obstaculo;

    /**
     * Constructs a new GameArenaView with the specified grid, snake, food, score, and obstacles.
     *
     * @param grid The grid representing the game area.
     * @param snake The snake object in the game.
     * @param food The food object in the game.
     * @param score The score object tracking the player's score.
     * @param obstaculo The obstacle object in the game.
     */
    public GameArenaView(Grid grid, Snake snake, Food food, Score score, Obstaculo obstaculo) {
        this.grid = grid;
        this.snake = snake;
        this.food = food;
        this.score = score;
        this.obstaculo = obstaculo;
        this.obstaculo.placeObstaculo(grid);
        setPreferredSize(new Dimension(grid.getWidth(), grid.getHeight()));
        setBackground(Color.BLACK);
    }

    /**
     * Overrides the paintComponent method to draw the game components.
     *
     * @param g The Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Draws the game components on the provided Graphics object.
     *
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g) {
        grid.draw(g);

    }

    /**
     * Updates the view by repainting the panel.
     */
    public void updateView() {
        repaint();
    }
}
