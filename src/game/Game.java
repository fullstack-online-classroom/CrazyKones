package game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.*;
public class Game {

    public static final int SPEED = 10;
    public static final String RESOURCES_PREFIX = "resources/";

    public boolean gameStarted = true;

    private Car car;

    private Picture gameOverPic;

    public List<Kone> Kones = new ArrayList<>();

    public Game() {
        Picture gameOverPic = new Picture(0, 0, Game.RESOURCES_PREFIX + "gameOverScreen.png");
        this.gameOverPic = gameOverPic;
    }

    public void start() throws InterruptedException {
        int counter = 0;
        while (gameStarted) {
            counter++;
            moveKones();
            if (Kones.size() == 8) {
                Kones.remove(0);
            }
            System.out.println(Kones);
            if(counter >= 3) {
                createKone();
                counter = 0;
            }
            Thread.sleep(1000);
        }
    }

    public void createKone(){
        Kone kone = new Kone(new Position());
        Kones.add(kone);
    }

    public void moveKones(){
        for(Kone kone: Kones){
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
        }
    }

    public void gameRestart(){
        if (!gameStarted) {
            gameStarted = true;
            gameOverPic.delete();
            moveKones(); //it only works once after dying idk why
        }
    }

    public void setCar(Car car){
        this.car = car;
    }
}
