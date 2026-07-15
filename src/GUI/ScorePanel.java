package GUI;

import SnakeGame.Score;

import javax.swing.*;
import java.awt.*;

/**
 * ScorePanel class represents the panel that displays the current score of the game.
 * It is a graphical component that updates and redraws the score as the game progresses.
 */
public class ScorePanel extends JPanel {
    private Score score;

    /**
     * Constructs a ScorePanel with the specified Score object.
     *
     * @param score_ The score object representing the current score of the game.
     */
    public ScorePanel(Score score_) {
        this.score = score_;
        setPreferredSize(new Dimension(800, 25)); // Width of the grid, height of the score panel
        setBackground(Color.BLACK);
    }

    /**
     * Paints the score on the panel.
     *
     * @param g The Graphics object used for drawing the score.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        score.draw(g);
    }

    /**
     * Updates the score panel by triggering a repaint.
     */
    public void updateScorePanel() {
        repaint();
    }
}
