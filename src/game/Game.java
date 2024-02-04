package game;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    public static int SPEED = Speed.MEDIUM.getSpeedValue(); //acessed by enum???????????????????????????????????????

    public static final String RESOURCES_PREFIX = "resources/";

    private boolean gameStarted = true;

    private Car car;

    private ObstacleFactory obstacleFactory;

    private Picture gameOverPic;

    private Score score;

    private ScoreManager scoreManager;
    private Text finalScoreText; // Add this field to keep track of the final score text

    public Game() {
        scoreManager = new ScoreManager();
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

    private void moveObstacles(){
        //List<Obstacle> obstacleArray = obstacleFactory.getObstacles();
        for(Obstacle obstacle: obstacleFactory.getObstacles()) {
            if (!gameStarted) {
                return;
            }
            obstacle.move();
            if (!car.getGodMode()) {
                colissionDetector(obstacle);
            }
        }
    }


    private void colissionDetector(Obstacle obstacle) {

        Picture obstaclePic = obstacle.getPic();
        Picture carPic = car.getCarPic();

            if (carPic.getX() + carPic.getWidth() >= obstaclePic.getX() && carPic.getY() + carPic.getHeight() >= obstaclePic.getY() &&
                    carPic.getY() <= obstaclePic.getY() + obstaclePic.getHeight() && carPic.getX() <= obstaclePic.getX() + obstaclePic.getWidth()) {
                gameOver();
            }
    }

    private void gameOver(){
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
            //kones = new ArrayList<>(); //call obstacleFactory????????   THIS NEEDS TO CHANGE TO OBSTACLES
            gameStarted = true;
            // change the car image back to the original one
        }
    }

    //we could also have created a setObstacleFactory
    public void setCar(Car car){
        this.car = car;
    }
    public void setScore(Score score) {
        this.score = score;
    }

    public static void setSPEED(int SPEED){ //////////////////////////////////////////////////7
        Game.SPEED = SPEED;
    }
}
