package game;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardLogic implements KeyboardHandler {

    private Keyboard keyboard;
    private Car car;

    private Game game;

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

        KeyboardEvent enter = new KeyboardEvent();
        enter.setKey(KeyboardEvent.KEY_ENTER);
        enter.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(enter);
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
            if(game.CheckIfControlsAreOpen())
            {
                game.RemoveControls();
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}