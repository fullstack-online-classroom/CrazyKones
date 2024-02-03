package game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Client extends Obstacle implements Good{

    private Picture picture;

    public Client() {

        picture = new Picture(500, 500, Game.RESOURCES_PREFIX + "client.png");
        picture.draw();
    }
}
