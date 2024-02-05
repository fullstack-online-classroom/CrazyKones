package game;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.*;
public class Game {

    public static final int SPEED = 10;
    public static final String RESOURCES_PREFIX = "resources/";

    public boolean gameStarted = false;

    private Car car;

    private Kone kone;

    private Picture gameOverPic;
    private Picture controlsPic;

    public List<Kone> kones;
    private Score score;
    private ScoreManager scoreManager = new ScoreManager();
    private Text finalScoreText; // Add this field to keep track of the final score text



    public Game() {
        Picture gameOverPic = new Picture(0, 0, Game.RESOURCES_PREFIX + "gameOverScreen.png");
        this.gameOverPic = gameOverPic;
    }

    public void start() throws InterruptedException {
        gameStarted = true;

        int counter = 0;
        int dificulty = 1000;
        kones = new ArrayList<>();
        while (gameStarted) {
            System.out.println(controlsPic == null);
            if(controlsPic == null){
                counter++;
                moveKones();
                if (kones.size() == 8) {
                    kones.remove(0);
                }
                if(counter >= 3) {
                    createKone();
                    counter = 0;
                }
                Thread.sleep(dificulty--);
            }
        }
    }

    public void setUpScores(){
        Score score = new Score();
        score.startTimer();
        setScore(score); // set the score in the game
    }

    public void DrawControls(Background background){
        float centerPosX = background.getBackground().getWidth() /5;
        float centerPosY = background.getBackground().getHeight()/5;
        controlsPic = new Picture(centerPosX,centerPosY,Game.RESOURCES_PREFIX + "controls.png");
        controlsPic.draw();
    }

    public void RemoveControls(){
        if(controlsPic == null)return;
        controlsPic.delete();
        controlsPic = null;

        gameStarted = true;
        setUpScores();
        createKone();
    }

    public void createKone(){
        Kone kone = new Kone(new Position());
        kones.add(kone);
    }

    public void moveKones(){
        System.out.println("aaaasdasssssssssssssssd");
        for(Kone kone: kones){
            if (!gameStarted) {
                return;
            }
            kone.moveKone();
            colissionDetector(kone);
        }
    }

    public void colissionDetector(Kone kone){
        Picture konePic = kone.getKonePic();
        Picture carPic = car.getCarPic();

        if (carPic.getX() + carPic.getWidth() >= konePic.getX() && carPic.getY() + carPic.getHeight() >= konePic.getY() &&
                carPic.getY() <= konePic.getY() + konePic.getHeight() && carPic.getX() <= konePic.getX() + konePic.getWidth())
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
            removeKone();
            kones = new ArrayList<>();
            score.resetScore(); // reset the score
            gameStarted = true;
        }
    }

    public void removeKone(){
        for(int i = (kones.size() - 1); i >= 0 ; i--) {
            kones.get(i).getKonePic().delete();
            kones.remove(i);
        }
    }

    public void setCar(Car car){
        this.car = car;
    }
    public void setScore(Score score) {
        this.score = score;
    }
}
