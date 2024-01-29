package game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.*;
public class Game {

    public static final int SPEED = 10;
    public static final String RESOURCES_PREFIX = "resources/";

    public boolean gameStarted = true;

    private Car car;

    private Kone kone;

    private Picture gameOverPic;

    public List<Kone> kones;

    public Game() {
        Picture gameOverPic = new Picture(0, 0, Game.RESOURCES_PREFIX + "gameOverScreen.png");
        this.gameOverPic = gameOverPic;
    }

    public void start() throws InterruptedException {
        int counter = 0;
        int dificulty = 1000;
        kones = new ArrayList<>();
        while (gameStarted) {
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

    public void createKone(){
        Kone kone = new Kone(new Position());
        kones.add(kone);
    }

    public void moveKones(){
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
        }
    }

    public void gameRestart(){
        if (!gameStarted) {
            gameOverPic.delete();
            removeKone();
            kones = new ArrayList<>();
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
}
