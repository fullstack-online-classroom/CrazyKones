package gameObjects;

import game.Background;
import game.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class FireKone extends Obstacle implements Bad {

    private Picture fireKonePic;

    private int posX;
    private int posY;

    public FireKone() {
        super();
        posX = Background.getWidth()-120;
        posY = (int) Math.round(Math.random() * (Background.getHeight()-120));
        //posY = Background.getHeight();
        fireKonePic = new Picture(posX, posY, Game.RESOURCES_PREFIX + "fireKone.png");
        fireKonePic.draw();
        //move();
    }

    public void move(){ //need to improve to check if obst hit edge of image
        if(posX > 0){
            posX-=100;
            fireKonePic.translate(-200, 0);
        }
        else {
            fireKonePic.delete();
        }
    }
    public int minusPoint(){
        return 1;
    }

    public void deletePic(){
        fireKonePic.delete();
    }

    public Picture getPic(){
        return fireKonePic;
    }

}
