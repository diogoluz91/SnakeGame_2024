package SnakeGame;

import java.awt.*;

/**
 * Manages the score for the Snake game, including updating the score, setting the player's name, and drawing the score on the screen.
 */
public class Score {

    private String name;
    private int points;

    /**
     * Constructs a Score object with the specified name and initial score.
     *
     * @param name The name of the player.
     * @param points The initial score.
     */
    public Score(String name, int points) {
        this.name = name;
        this.points = points;
    }

    /**
     * Updates the score each time the food is eaten by the snake.
     *
     * @param p The points to add to the current score.
     */
    public void updateScore(int p) {
        this.points += p;
    }

    /**
     * Returns the current score.
     *
     * @return The current score.
     */
    public int getScore() {
        return this.points;
    }

    /**
     * Sets a new name for the score.
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Draws the score on the screen.
     *
     * @param g The graphics context to draw on.
     */
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(getScoreText(), 10, 20);
    }

    /**
     * Draws the score in the console with the current direction of the snake.
     *
     * @param x The x direction of the snake's movement.
     * @param y The y direction of the snake's movement.
     */
    public void drawTxt(int x, int y) {
        int dir = 0;
        if (x == 1) {
            dir = 0;
        } else if (x == -1) {
            dir = 180;
        } else if (y == -1) {
            dir = 90;
        } else if (y == 1) {
            dir = 270;
        }
        System.out.println("Dir: " + dir + "               Score: " + points);
    }

    /**
     * Returns the score as a text string.
     *
     * @return The score as a text string.
     */
    public String getScoreText() {
        return "Score: " + this.points;
    }
}
