package gameObjects;

import game.Background;
import game.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;
public class Kone extends Obstacle implements Bad {

    private Picture konePic;

    private int posX;
    private int posY;

    public Kone() {
        super();
        posX = Background.getWidth()-120;
        posY = (int) Math.round(Math.random() * (Background.getHeight()-120));
        //posY = Background.getHeight();
        konePic = new Picture(posX, posY, Game.RESOURCES_PREFIX + "kone.png");
        konePic.draw();
        move();
    }

    public void move(){ //need to improve to check if obst hit edge of image
        if(posX > 0){
            posX-=100;
            konePic.translate(-200, 0);
        }
        else {
            konePic.delete();
        }
    }
    public int minusPoint(){
        return 1;
    }

    public void deletePic(){
        konePic.delete();
    }

    public Picture getPic(){
        return konePic;
    }
}
