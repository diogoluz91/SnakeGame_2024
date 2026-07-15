package JUnitTests;

import SnakeGame.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTests {

    private Score score;

    @BeforeEach
    public void setUp() {
        score = new Score("Player1", 0);
    }

    @Test
    public void testConstructor() {
        Score score = new Score("Player2", 100);
        assertEquals("Player2", score.getName());
        assertEquals(100, score.getScore());
    }

    @Test
    public void testUpdateScore() {
        score.updateScore(10);
        assertEquals(10, score.getScore());
        score.updateScore(5);
        assertEquals(15, score.getScore());
    }

    @Test
    public void testGetScore() {
        assertEquals(0, score.getScore());
        score.updateScore(5);
        assertEquals(5, score.getScore());
    }

    @Test
    public void testSetName() {
        score.setName("Player2");
        assertEquals("Player2", score.getName());
    }

    @Test
    public void testGetName() {
        assertEquals("Player1", score.getName());
    }


    @Test
    public void testGetScoreText() {
        assertEquals("Score: 0", score.getScoreText());
        score.updateScore(15);
        assertEquals("Score: 15", score.getScoreText());
    }
}
