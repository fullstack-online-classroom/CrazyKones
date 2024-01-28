package game;

import java.util.*;
public class Game {

    public static final int SPEED = 10;
    public static final String RESOURCES_PREFIX = "resources/";

    public boolean gameStarted = true;

    public List<Kone> Kones = new ArrayList<>();

    public void start() throws InterruptedException {
        int counter = 0;
        while (gameStarted) {
            counter++;
            moveKones();
            if(counter >= 3) {
                createKone();
                counter = 0;
                System.out.println("started");
            }
            Thread.sleep(1000);
        }
    }

    public void createKone(){
        Kone kone = new Kone(new Position());
        Kones.add(kone);
        return;
    }

    public void moveKones(){
        for(Kone kone: Kones){
            kone.moveKone();
        }
    }
}
