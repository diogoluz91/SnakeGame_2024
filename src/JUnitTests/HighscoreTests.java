package JUnitTests;

import SnakeGame.Highscore;
import SnakeGame.Score;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HighscoreTests {

    private Highscore highscore;
    private static final String FILE_PATH = "./Highscores.txt";

    @BeforeEach
    public void setUp() {
        // Setup: Create a clean Highscore instance and ensure the file does not exist
        highscore = new Highscore();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    public void tearDown() {
        // Cleanup: Delete the highscore file after each test
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testAddNewScore() {
        Score score = new Score("Player1", 100);
        highscore.addNewScore(score);
        List<Score> scores = highscore.getHighestScores();
        assertFalse(scores.isEmpty());
        assertEquals("Player1", scores.get(0).getName());
        assertEquals(100, scores.get(0).getScore());
    }

    @Test
    public void testSortScores() {
        Score score1 = new Score("Player1", 100);
        Score score2 = new Score("Player2", 200);
        highscore.addNewScore(score1);
        highscore.addNewScore(score2);
        List<Score> scores = highscore.getHighestScores();
        assertEquals("Player2", scores.get(0).getName());
        assertEquals(200, scores.get(0).getScore());
    }



    @Test
    public void testWriteScoreFile() {
        Score score = new Score("Player1", 100);
        highscore.addNewScore(score);
        highscore.writeScoreFile();

        // Read the file manually to verify the content
        try {
            List<String> lines = Files.readAllLines(new File(FILE_PATH).toPath());
            assertEquals(1, lines.size());
            assertEquals("Player1 100", lines.get(0));
        } catch (IOException e) {
            fail("Failed to read test data from file");
        }
    }

    @Test
    public void testGetHighestScores() {
        Score score1 = new Score("Player1", 100);
        Score score2 = new Score("Player2", 200);
        highscore.addNewScore(score1);
        highscore.addNewScore(score2);
        List<Score> scores = highscore.getHighestScores();
        assertEquals(2, scores.size());
        assertEquals("Player2", scores.get(0).getName());
        assertEquals(200, scores.get(0).getScore());
    }

    @Test
    public void testPrintHighscores() {
        Score score1 = new Score("Player1", 100);
        Score score2 = new Score("Player2", 200);
        highscore.addNewScore(score1);
        highscore.addNewScore(score2);

        highscore.printHighscores();
        // Since this method prints to the console, you might need to verify this manually or redirect stdout in tests.
    }

    @Test
    public void testPrintScore() {
        Score score1 = new Score("Player1", 100);
        Score score2 = new Score("Player2", 200);
        highscore.addNewScore(score1);
        highscore.addNewScore(score2);

    }
}
