package gameObjects;

import game.Background;
import game.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Hole extends Obstacle implements Bad {

    private Picture holePic;

    private int posX;
    private int posY;

    public Hole() {
        super();
        posX = Background.getWidth()-120;
        posY = (int) Math.round(Math.random() * (Background.getHeight()-120));
        //posY = Background.getHeight();
        holePic = new Picture(posX, posY, Game.RESOURCES_PREFIX + "hole.png");
        holePic.draw();
        move();
    }

    public void move(){ //need to improve to check if obst hit edge of image
        if(posX > 0){
            posX-=100;
            holePic.translate(-200, 0);
        }
        else {
            holePic.delete();
        }
    }
    public int minusPoint(){
        return 1;
    }

    public void deletePic(){
        holePic.delete();
    }

    public Picture getPic(){
        return holePic;
    }

}
