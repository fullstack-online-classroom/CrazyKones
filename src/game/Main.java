package game;

import gameObjects.Car;
import gameObjects.Obstacle;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        //Canvas.setMaxX(1200);
        //Canvas.setMaxY(700);
        // x, y, width, height

        Sound sound = new Sound("start.wav");
        //sound.play();

        Game game = new Game();

        Background background = new Background();
        Car car = new Car();
        car.setBackground(background);

        KeyboardLogic keyboardLogic = new KeyboardLogic();
        keyboardLogic.setCar(car);
        keyboardLogic.setGame(game);

        game.setCar(car);
        car.setGame(game);
        CollisionDetector collisionDetector = new CollisionDetector();
        game.setColissionDetector(collisionDetector);
        collisionDetector.setGame(game);
        Obstacle.setCar(car);/////////////////////////////////////////////////

        game.DrawControls(background);
        game.start();
    }
}
