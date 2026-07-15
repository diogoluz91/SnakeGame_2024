package GUI;

import FigurasGeo.Ponto;
import SnakeGame.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
 * GameController class handles the game logic and user interactions for the Snake Game.
 * It manages the movement of the snake, collision detection, food consumption, and game over conditions.
 */
public class GameController implements KeyListener, ActionListener {
    private Snake snake;
    private Food food;
    private Grid grid;
    private GameArenaView view;
    private Timer timer;
    private Score score;
    private ScorePanel scorePanel;
    private boolean automaticInput;
    private boolean isFoodCircle;
    private JFrame gameFrame;

    /**
     * Constructs a GameController with the specified game components and settings.
     *
     * @param snake The snake object.
     * @param food The food object.
     * @param grid The grid object representing the game area.
     * @param view The game arena view object for rendering the game.
     * @param score The score object for tracking the score.
     * @param scorePanel The score panel object for displaying the score.
     * @param automaticInput Flag indicating if the snake movement is automatic.
     * @param isFoodCircle Flag indicating if the food is circular.
     * @param gameFrame_ The main game frame.
     */
    public GameController(Snake snake, Food food, Grid grid, GameArenaView view, Score score, ScorePanel scorePanel, boolean automaticInput, boolean isFoodCircle, JFrame gameFrame_) {
        this.snake = snake;
        this.food = food;
        this.grid = grid;
        this.view = view;
        this.score = score;
        this.scorePanel = scorePanel;
        this.automaticInput = automaticInput;
        this.isFoodCircle = isFoodCircle;
        this.gameFrame = gameFrame_;

        timer = new Timer(100, this);
        timer.start();
    }

    /**
     * Handles key pressed events to control the snake's direction.
     * Makes sure the snake can't go backwards.
     *
     * @param e The key event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (snake.getVelocityY() != 1) {
                    snake.setVelocityY(-1);
                    snake.setVelocityX(0);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snake.getVelocityY() != -1) {
                    snake.setVelocityY(1);
                    snake.setVelocityX(0);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (snake.getVelocityX() != 1) {
                    snake.setVelocityY(0);
                    snake.setVelocityX(-1);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snake.getVelocityX() != -1) {
                    snake.setVelocityY(0);
                    snake.setVelocityX(1);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Handles timer events to update the game state.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (snake.checkWallCollision() || snake.checkObstacleCollision() || snake.checkBodyHeadCollision(new Ponto<>(food.getFoodPonto().getX(), food.getFoodPonto().getY()))) {
                gameOver();
            }
            if (automaticInput) {
                snake.autoMove(food);
            } else {
                snake.move(food);
            }
            checkFoodCollision();
            view.updateView();
            scorePanel.updateScorePanel();
        } catch (IllegalArgumentException ex) {
            gameOver();
        }
    }

    /**
     * Checks if the snake has collided with the food.
     * If a collision is detected, the food is relocated, and the score is updated.
     */
    private void checkFoodCollision() {
        int x =  food.getFoodPonto().getX();
        int y =  food.getFoodPonto().getY();
        Ponto<Integer> foodPosition = new Ponto<>(x, y);

        if (snake.checkFoodColision(foodPosition)) {
            food.placeFoodRandomInt(grid.getWidth(), grid.getHeight());
            this.score.updateScore(1);
        }
    }

    /**
     * Ends the game and displays the game over frame.
     */
    private void gameOver() {
        timer.stop();
        SwingUtilities.invokeLater(() -> {
            GameOverFrame gameOverFrame = new GameOverFrame(score, new Highscore(), gameFrame);
            gameOverFrame.setVisible(true);
        });
    }
}
