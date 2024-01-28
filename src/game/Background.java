package game;

import game.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Background {

    private Picture background;
    private static int backgroundWidth;
    private static int backgroundHeight;

    public Background() {
        background = new Picture(0, 0, Game.RESOURCES_PREFIX + "road.png");
        background.draw();
        backgroundWidth = background.getMaxX();
        backgroundHeight = background.getMaxY();
    }


    public static int getWidth() {
        return backgroundWidth;
    }

    public static int getHeight(){
        return backgroundHeight;
    }

    public Picture getBackground() {
        return background;
    }

    public void moveRight(){
        background.translate(Game.SPEED, 0);
    }

    public void moveLeft(){
        background.translate(-Game.SPEED, 0);
    }
}
