package game;
import java.io.*;

public class ScoreManager {
    private static final String HIGH_SCORE_FILE = "highscore.txt";

    public int readHighScore() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE));
            String line = reader.readLine();
            reader.close();
            return line != null ? Integer.parseInt(line) : 0;
        } catch (IOException e) {
            return 0;
        }
    }

    public void writeHighScore(int score) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE));
            writer.write(String.valueOf(score));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
