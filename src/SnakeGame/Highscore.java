package SnakeGame;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the high scores for the Snake game, including reading from and writing to a file,
 * sorting the scores, and displaying them.
 */
public class Highscore {

    private List<Score> scores;
    private static final String FILE_PATH = "./Highscores.txt";

    /**
     * Constructs a Highscore object and reads the high scores from the file.
     */
    public Highscore() {
        this.scores = new ArrayList<>();
        readScoreFile();
    }

    /**
     * Adds a new score to the list, sorts the scores, and writes the updated list to the file.
     *
     * @param s The score to add.
     */
    public void addNewScore(Score s) {
        this.scores.add(s);
        sortScores();
        writeScoreFile();
    }

    /**
     * Sorts the scores in descending order.
     */
    public void sortScores() {
        Collections.sort(this.scores, (s1, s2) -> s2.getScore() - s1.getScore());
    }

    /**
     * Reads the high scores from the file.
     */
    public void readScoreFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strs = line.split(" ");
                if (strs.length == 2) {
                    String name = strs[0];
                    int score;
                    score = Integer.parseInt(strs[1]);

                    Score scoreObj = new Score(name, score);
                    this.scores.add(scoreObj);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
            sortScores();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Writes the high scores to the file.
     */
    public void writeScoreFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Score score : this.scores) {
                writer.write(score.getName() + " " + score.getScore() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Returns the list of high scores.
     *
     * @return The list of high scores.
     */
    public List<Score> getHighestScores() {
        return this.scores;
    }

    /**
     * Prints the top 5 high scores.
     */
    public void printHighscores() {
        System.out.println("\nHighest Recorded Scores!");
        for (int i = 0; i < 5; i++) {
            System.out.println(this.scores.get(i).getName() + " -> " + this.scores.get(i).getScore() + " points.");
        }
    }

    /**
     * Prints all the scores.
     */
    public void printAllScore() {
        for (Score score : this.scores) {
            System.out.println(score.getScore());
        }
    }

}
