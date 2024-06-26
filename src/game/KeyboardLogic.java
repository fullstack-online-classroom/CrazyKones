package game;

import gameObjects.Car;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardLogic implements KeyboardHandler {

    private Keyboard keyboard;
    private Car car;

    private Game game;

    private boolean sKeyPressed;
    private boolean rightArrowKeyPressed;
    private boolean fKeyPressed;



    public KeyboardLogic() {
        keyboard = new Keyboard(this);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(right);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(left);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(up);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down);

        KeyboardEvent five = new KeyboardEvent();
        five.setKey(KeyboardEvent.KEY_5);
        five.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(five);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(space);

        KeyboardEvent fKey = new KeyboardEvent();
        fKey.setKey(KeyboardEvent.KEY_F);
        fKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(fKey);
        //testing
        KeyboardEvent releaseFKey = new KeyboardEvent();
        releaseFKey.setKey(KeyboardEvent.KEY_F);
        releaseFKey.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(releaseFKey);

        KeyboardEvent sKey = new KeyboardEvent();
        sKey.setKey(KeyboardEvent.KEY_S);
        sKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(sKey);
        //testing
        KeyboardEvent releaseSKey = new KeyboardEvent();
        releaseSKey.setKey(KeyboardEvent.KEY_S);
        releaseSKey.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(releaseSKey);

        KeyboardEvent enter = new KeyboardEvent();
        enter.setKey(KeyboardEvent.KEY_ENTER);
        enter.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(enter);

        KeyboardEvent exit = new KeyboardEvent();
        exit.setKey(KeyboardEvent.KEY_ESC);
        exit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(exit);

    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setGame(Game game) { this.game = game; }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        /*if(keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT){
            //setCurrentDirection(Direction.RIGHT);  character.setDirection(RIGHT);
            car.moveRight();
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT){
            car.moveLeft();
        }*/

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_UP){
            car.moveUp();
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN){
            car.moveDown();
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_5){
            car.easyMode();
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE){
                game.gameRestart();
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER){
            game.RemoveControls();
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_ESC){
            game.ExitGame();
        }

        // cheating code to change the car image
        if(keyboardEvent.getKey() == KeyboardEvent.KEY_F){
            fKeyPressed = true;
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_S){
            sKeyPressed = true;
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT){
            rightArrowKeyPressed = true;
        }

        if(fKeyPressed && rightArrowKeyPressed){
            car.changeToFerrari();
        }

        if(sKeyPressed && rightArrowKeyPressed){
            car.changeToSnail();
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT){
            rightArrowKeyPressed = false;
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_F){
            fKeyPressed = false;
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_S){
            sKeyPressed = false;
        }

    }
}