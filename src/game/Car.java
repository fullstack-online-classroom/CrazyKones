package game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Car {

    private Background background;

    private Picture carPic;

    private int posX = 0;
    private int posY = 400;

    private String carRight;
    private String carLeft;

    private String carEasy;
    private String carUp;
    private String carDown;

    private boolean tiny;


    public Car(){
        carRight = Game.RESOURCES_PREFIX + "carStandard.png";
        carLeft = Game.RESOURCES_PREFIX + "carLeft.png";
        carUp = Game.RESOURCES_PREFIX + "carUp.png";
        carDown = Game.RESOURCES_PREFIX + "carDown.png";
        carEasy = Game.RESOURCES_PREFIX + "carEasy.png";
        carPic = new Picture(posX, posY, carRight);
        carPic.load(carRight);
        carPic.draw();
    }
    //picture = new Picture(STARTING_X, STARTING_Y, "Resources/catiaStreetStore.PNG"); //Create Character model on the screen

    public void setBackground(Background background) {
        this.background = background;
    }

    public Picture getCarPic(){
        return carPic;
    }
    public void moveUp() {

        if (carPic.getY() - Game.SPEED >= 0) {
            carPic.translate(posX, -Game.SPEED);
            posY-=Game.SPEED;
        }
    }

    public void moveDown(){
        if (carPic.getY() + carPic.getHeight() + Game.SPEED <= background.getHeight()) {
            carPic.translate(posX, Game.SPEED);
            posY+=Game.SPEED;
        }
    }

    public void easyMode() {
        if (!tiny) {
            carPic.delete();
            carPic = new Picture(posX, posY, carEasy);
            carPic.load(carEasy);
            carPic.draw();
            tiny = true;
        }
        else if (tiny) {
            carPic.delete();
            carPic = new Picture(posX, posY, carRight);
            carPic.load(carRight);
            carPic.draw();
            tiny = false;
        }
    }

    public void moveLeft(){

        if (carPic.getX() - Game.SPEED >= 0) {
            carPic.load(carLeft);
            carPic.translate(-Game.SPEED, 0);
        }
    }

    public void moveRight(){

        if(carPic.getX() + carPic.getWidth() + Game.SPEED <= background.getWidth()){
            carPic.load(carRight);
            carPic.translate(Game.SPEED, 0);}
    }
}

