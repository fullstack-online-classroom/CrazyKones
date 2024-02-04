package game;

import gameObjects.Car;
import gameObjects.Obstacle;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    public static int SPEED;

    public static final String RESOURCES_PREFIX = "resources/";

    private boolean gameStarted = true;

    private Car car;

    private ObstacleFactory obstacleFactory;

    private Picture gameOverPic;

    private Score score;

    private ScoreManager scoreManager;
    private Text finalScoreText; // Add this field to keep track of the final score text

    private CollisionDetector colissionDetector;

    public Game() {
        SPEED = Speed.MEDIUM.getSpeedValue();//acessed by enum
        scoreManager = new ScoreManager();
        obstacleFactory = new ObstacleFactory();
        Picture gameOverPic = new Picture(0, 0, Game.RESOURCES_PREFIX + "gameOverScreen.png");
        this.gameOverPic = gameOverPic;
    }

    public void start() throws InterruptedException { //missing a catch
        int randomizer = 0; //1 to 5 we need to create a strategy DP to replace this
        while (gameStarted) {
            moveObstacles();
            randomizer = (int) (Math.random() * 5) + 1;
            //change to when it hits the border of background
            if (obstacleFactory.getObstacles().size() == 8) {
                obstacleFactory.getObstacles().remove(0);
            }
            if(randomizer >= 3) {
                obstacleFactory.addKone();
            }
            if(randomizer < 3) {
                obstacleFactory.addClient();
            }
            Thread.sleep(1000);
        }
    }

    private void moveObstacles(){
        colissionDetector.setCar(car);
        for(Obstacle obstacle: obstacleFactory.getObstacles()) {
            if (!gameStarted) {
                return;
            }
            obstacle.move();
            if (!car.getGodMode()) {
                colissionDetector.collision(obstacle);
            }
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
                finalScoreText.grow(100, 40); //text box size
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
            score.resetScore(); // reset the score
            car.changeToTaxi();
            obstacleFactory.removeObstacles();
            gameStarted = true;
        }
    }

    public void setCar(Car car){
        this.car = car;
    }

    public void setColissionDetector(CollisionDetector colissionDetector){
        this.colissionDetector = colissionDetector;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public static void setSpeed(int speed){ //static works here since there is only 1game1car we change class prop
        Game.SPEED = speed;
    }

}
