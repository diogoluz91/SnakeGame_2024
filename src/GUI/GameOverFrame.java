package GUI;

import SnakeGame.Highscore;
import SnakeGame.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * GameOverFrame class represents the frame displayed when the game is over.
 * It allows the player to view their score, enter their name for the highscore list,
 * replay the game, or exit.
 */
public class GameOverFrame extends JFrame {
    private JTextField nameField;
    private JButton replayButton;
    private JButton exitButton;
    private Score score;
    private Highscore highscore;
    private JFrame gameFrame;
    private JTextArea highscoreArea;

    /**
     * Constructs a GameOverFrame with the specified score, highscore list, and game frame.
     *
     * @param score The score object representing the player's score.
     * @param highscore The highscore object representing the list of high scores.
     * @param gameFrame_ The main game frame.
     */
    public GameOverFrame(Score score, Highscore highscore, JFrame gameFrame_) {
        this.score = score;
        this.highscore = highscore;
        this.gameFrame = gameFrame_;

        setTitle("Game Over");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Game Over Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Fim do Jogo!!"), gbc);

        // Current Score Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("O seu Score é:"), gbc);

        // Score Value Label
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JLabel(String.valueOf(score.getScore())), gbc);

        // Enter Name Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Introduza o seu nome:"), gbc);

        // Name TextField
        gbc.gridx = 1;
        gbc.gridy = 2;
        nameField = new JTextField(10);
        panel.add(nameField, gbc);

        // Replay Button
        replayButton = new JButton("Jogar Novamente");
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScore();
                restartGame();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(replayButton, gbc);

        // Exit Button
        exitButton = new JButton("Fechar Jogo");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScore();
                System.exit(0);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(exitButton, gbc);

        // Highscore Area
        highscoreArea = new JTextArea(10, 30);
        highscoreArea.setEditable(false);
        updateHighscoreArea();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(new JScrollPane(highscoreArea), gbc);

        add(panel);
    }

    /**
     * Saves the player's score and name to the highscore list.
     */
    private void saveScore() {
        String playerName = nameField.getText();
        score.setName(playerName);
        highscore.addNewScore(score);
        updateHighscoreArea();
    }

    /**
     * Updates the highscore area with the top scores.
     */
    private void updateHighscoreArea() {
        List<Score> topScores = highscore.getHighestScores();
        if (topScores != null && !topScores.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Highscores:\n");
            for (int i = 0; i < Math.min(topScores.size(), 5); i++) {
                Score s = topScores.get(i);
                sb.append((i + 1) + ". " + s.getName() + ": " + s.getScore() + "\n");
            }
            highscoreArea.setText(sb.toString());
        } else {
            highscoreArea.setText("No highscores available.");
        }
    }

    /**
     * Restarts the game by disposing of the current frames and launching a new GameConfigFrame.
     */
    private void restartGame() {
        gameFrame.dispose();
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            GameConfigFrame configFrame = new GameConfigFrame();
            configFrame.setVisible(true);
        });
    }
}
