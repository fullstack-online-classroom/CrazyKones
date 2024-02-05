package gameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Obstacle {

    public Picture pic;

    public Obstacle(){
    }
    public void deletePic() {
    } //implement here instead of each object

    public Picture getPic(){
        return pic;
    }

    public abstract void move();

}
