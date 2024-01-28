package game;

import org.academiadecodigo.simplegraphics.graphics.Canvas;

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
        //Client client = new Client();
        car.setBackground(background);

        KeyboardLogic keyboardLogic = new KeyboardLogic();
        keyboardLogic.setCar(car);
        game.setCar(car);

        game.start();

    }
}
