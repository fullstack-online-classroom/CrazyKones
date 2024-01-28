package game;

import org.academiadecodigo.simplegraphics.pictures.Picture;
public class Kone {

    private Picture konePic;

    private int posX;

    private int posY;

    public Kone(Position position) {
        posX = Background.getWidth()-120;
        posY = (int) Math.round(Math.random() * (Background.getHeight()-120));
        //posY = Background.getHeight();
        konePic = new Picture(posX, posY, Game.RESOURCES_PREFIX + "kone.png");
        konePic.draw();
        moveKone();
    }

    public void moveKone(){
        if(posX > 0){
            posX-=100;
            System.out.println("moved" + posX);
            konePic.translate(-100, 0);
        }
        else
            konePic.delete();
    }
}
