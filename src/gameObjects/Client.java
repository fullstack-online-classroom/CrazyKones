package gameObjects;

import game.Background;
import game.Game;
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

    public void move(){ //need to improve to check if obst hit edge of image
        if(posX > 0){
            posX-=100;
            clientPic.translate(-200, 0);
        }
        else {
            clientPic.delete();
        }
    }

    public int plusPoint(){
        return 2;
    }

    public void deletePic() {
        clientPic.delete();
    }

    public Picture getPic(){
        return clientPic;
    }
}
