package game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Client extends Obstacle implements Good {

    private Picture clientPic;

    private int posX;
    private int posY;

    public Client() {
        super();
        posX = Background.getWidth()-120;
        posY = (int) Math.round(Math.random() * (Background.getHeight()-120));
        clientPic = new Picture(posX, posY, Game.RESOURCES_PREFIX + "client.png");
        clientPic.draw();
        move();
    }

    public void move(){
        if(posX > 0){
            posX-=100;
            clientPic.translate(-200, 0);
        }
        else {
            clientPic.delete();
        }
    }
    public void deletePic() {
        clientPic.delete();
    }

    public Picture getPic(){
        return clientPic;
    }
}
