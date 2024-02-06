package gameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Obstacle {

    public Picture pic;
    public static Car car;

    public Obstacle(){
    }
    public void deletePic() {
    } //implement here instead of each object

    public Picture getPic(){
        return pic;
    }

    public abstract void move();

    public static void setCar(Car car){///////////////////////////////
        Obstacle.car = car;
    }

}
