package SnakeGame;

import java.util.Scanner;

/**
 * Handles the game over sequence, including recording the player's score and displaying the high scores.
 */
public class GameOver {
    private Highscore hs;
    private Score s;

    /**
     * Constructs a GameOver object with the given score.
     * Prompts the player for their name to record their score, adds the score to the high scores, sorts the high scores, and prints the high scores.
     *
     * @param s The score object representing the player's score.
     */
    public GameOver(Score s) {
        this.s = s;
        this.hs = new Highscore();
        getName();
        this.hs.addNewScore(s);
        this.hs.sortScores();
        this.hs.printHighscores();
    }

    /**
     * Prompts the player to enter their name to record their score.
     */
    public void getName() {
        System.out.print("\nInsert your name to record your score:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        s.setName(name);
    }
}
