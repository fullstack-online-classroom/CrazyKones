package game;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import java.util.Timer;
import java.util.TimerTask;
public class Score {

private int score;
private Timer timer;
private Text scoreText;

public Score() {
    score = 0;
    timer = new Timer();
    scoreText = new Text(100, 50, "Score: " + score); // updated position
    scoreText.setColor(Color.BLUE); // updated color
    scoreText.grow(50, 40);
    scoreText.draw();
}

public void startTimer() {
    timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            score++;
            scoreText.setText("Score: " + score); // updated text
        }
    }, 0, 1000);
}
    public void resetScore() {
        score = 0;
        scoreText.setText("Score: " + score);
    }
public int getScore() {
    return score;
}
}