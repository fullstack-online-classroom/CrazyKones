package game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Car {

    private Background background;

    private Picture car;

    private String carRight;
    private String carLeft;
    private String carUp;
    private String carDown;

    public Car(){
        carRight = Game.RESOURCES_PREFIX + "carRight.png";
        carLeft = Game.RESOURCES_PREFIX + "carLeft.png";
        carUp = Game.RESOURCES_PREFIX + "carUp.png";
        carDown = Game.RESOURCES_PREFIX + "carDown.png";
        car = new Picture(0, 400, carRight);
        car.draw();
    }
    //picture = new Picture(STARTING_X, STARTING_Y, "Resources/catiaStreetStore.PNG"); //Create Character model on the screen

    public void setBackground(Background background) {
        this.background = background;
    }

    public void moveLeft(){

        if (car.getX() - Game.SPEED >= 0) {
            car.load(carLeft);
            car.translate(-Game.SPEED, 0);
        }
    }

    public void moveRight(){

        if(car.getX() + car.getWidth() + Game.SPEED <= background.getWidth()){
            car.load(carRight);
            car.translate(Game.SPEED, 0);}
    }

    public void moveUp() {

        if (car.getY() - Game.SPEED >= 0) {
            car.load(carRight);
            car.translate(0, -Game.SPEED);
        }
    }

    public void moveDown(){
        if (car.getY() + car.getHeight() + Game.SPEED <= background.getHeight()) {
            car.load(carRight);
            car.translate(0, Game.SPEED);
        }
    }
}

