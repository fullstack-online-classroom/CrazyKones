package game;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardLogic implements KeyboardHandler {

    private Keyboard keyboard;
    private Car car;

    private Game game;

    private boolean sKeyPressed = false;
    private boolean rightArrowKeyPressed = false;

    private boolean fKeyPressed = false;



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

        KeyboardEvent sKey = new KeyboardEvent();
        sKey.setKey(KeyboardEvent.KEY_S);
        sKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(sKey);
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
            car.changeToCheatImage();
        }
        if(sKeyPressed && rightArrowKeyPressed){
            car.changeToSnailImage();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}