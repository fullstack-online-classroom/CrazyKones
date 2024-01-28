package game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.*;
public class Game {

    public static final int SPEED = 10;
    public static final String RESOURCES_PREFIX = "resources/";

    public boolean gameStarted = true;

    private Car car;

    public List<Kone> Kones = new ArrayList<>();

    public void start() throws InterruptedException {
        int counter = 0;
        while (gameStarted) {
            counter++;
            moveKones();
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

        if (carPic.getX() + carPic.getWidth() >= konePic.getX() && carPic.getY() + carPic.getWidth() >= konePic.getY() &&
                carPic.getY() <= konePic.getY() + konePic.getHeight() && carPic.getX() <= konePic.getX() + konePic.getWidth())
        {
                gameStarted = false;
                System.out.println("game over");
        }
    }

    public void setCar(Car car){
        this.car = car;
    }
}
