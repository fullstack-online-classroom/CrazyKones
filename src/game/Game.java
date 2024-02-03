package game;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.*;
public class Game {

    public static final int SPEED = 10;
    public static final String RESOURCES_PREFIX = "resources/";

    public boolean gameStarted = true;

    private Car car;

    private ObstacleFactory obstacleFactory;

    private Picture gameOverPic;

    public List<Kone> kones;
    private Score score;
    private ScoreManager scoreManager = new ScoreManager();
    private Text finalScoreText; // Add this field to keep track of the final score text

    public Game() {
        obstacleFactory = new ObstacleFactory();
        Picture gameOverPic = new Picture(0, 0, Game.RESOURCES_PREFIX + "gameOverScreen.png");
        this.gameOverPic = gameOverPic;
    }

    public void start() throws InterruptedException {
        //List<Obstacle> obstacleArray = obstacleFactory.getObstacles();
        int randomizer = 0; //1 to 5
        //kones = new ArrayList<>();
        while (gameStarted) {
            moveObstacles();
            randomizer = (int) (Math.random() * 5) + 1;
            //change to when it hits the border of background
            if (obstacleFactory.getObstacles().size() == 8) {
                obstacleFactory.getObstacles().remove(0);
            }
            if(randomizer >= 3) {
                obstacleFactory.addKone();
                ;
            }
            if(randomizer < 3) {
                obstacleFactory.addClient();
            }
            Thread.sleep(1000);
        }
    }

    public void moveObstacles(){
        //List<Obstacle> obstacleArray = obstacleFactory.getObstacles();
        for(Obstacle obstacle: obstacleFactory.getObstacles()){
            if (!gameStarted) {
                return;
            }
            obstacle.move();
            colissionDetector(obstacle);
        }
    }


    public void colissionDetector(Obstacle obstacle) {

        Picture obstaclePic = obstacle.getPic();
        Picture carPic = car.getCarPic();

        if (carPic.getX() + carPic.getWidth() >= obstaclePic.getX() && carPic.getY() + carPic.getHeight() >= obstaclePic.getY() &&
                carPic.getY() <= obstaclePic.getY() + obstaclePic.getHeight() && carPic.getX() <= obstaclePic.getX() + obstaclePic.getWidth())
        {
                gameOver();
        }
    }

    public void gameOver(){
        if (gameStarted) {
            gameStarted = false;
            gameOverPic.draw();

            int finalScore = score.getScore();
            int highScore = scoreManager.readHighScore();

            // Only update the high score and draw the score if the final score is greater
            if (finalScore > highScore) {
                scoreManager.writeHighScore(finalScore);
                if (finalScoreText != null) {
                    finalScoreText.delete();
                }
                finalScoreText = new Text(893, 50, "High Score: " + finalScore);
                finalScoreText.setColor(Color.RED);
                finalScoreText.grow(100, 40);
                finalScoreText.draw();
            } else {
                // If the final score is not greater, draw the high score
                if (finalScoreText != null) {
                    finalScoreText.delete();
                }
                finalScoreText = new Text(893, 50, "High Score: " + highScore);
                finalScoreText.setColor(Color.RED);
                finalScoreText.grow(100, 40);
                finalScoreText.draw();
            }
        }
    }

    public void gameRestart(){
        if (!gameStarted) {
            gameOverPic.delete();
            obstacleFactory.removeObstacles();
            kones = new ArrayList<>(); //call obstacleFactory????????   THIS NEEDS TO CHANGE TO OBSTACLES
            score.resetScore(); // reset the score
            gameStarted = true;
        }
    }

    //we could also have created a setObstacleFactory
    public void setCar(Car car){
        this.car = car;
    }
    public void setScore(Score score) {
        this.score = score;
    }
}
