package game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Client {

    private Picture picture;

    public Client() {

        picture = new Picture(500, 500, Game.RESOURCES_PREFIX + "client.png");
        picture.draw();
    }
}
