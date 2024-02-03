package game;

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

    public void move(){
        if(posX > 0){
            posX-=100;
            konePic.translate(-200, 0);
        }
        else {
            konePic.delete();
        }
    }

    public void deletePic(){
        konePic.delete();
    }

    public Picture getPic(){
        return konePic;
    }
}
