package gameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Obstacle {


    public void deletePic() {
    }

    public Picture getPic(){
        return new Picture();
    }

    public abstract void move();

}
