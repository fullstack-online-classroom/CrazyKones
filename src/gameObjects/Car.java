package gameObjects;

import game.Background;
import game.Game;
import game.Speed;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.swing.*;

public class Car {

    private Background background;

    private Picture carPic;

    private int posX = 0;
    private int posY = 400;

    private String carRight;
    private String carLeft;

    private String carTiny;
    private String carUp;
    private String carDown;

    private boolean taxi;

    private boolean godMode;

    private String ferrari;
    private String snail;


    public Car(){
        carRight = Game.RESOURCES_PREFIX + "carStandard.png";
        carLeft = Game.RESOURCES_PREFIX + "carLeft.png";
        carUp = Game.RESOURCES_PREFIX + "carUp.png";
        carDown = Game.RESOURCES_PREFIX + "carDown.png";
        carTiny = Game.RESOURCES_PREFIX + "carTiny.png";
        ferrari = Game.RESOURCES_PREFIX + "ferrari.png";
        snail = Game.RESOURCES_PREFIX + "Snail.png";

        carPic = new Picture(posX, posY, carRight);
        carPic.load(carRight);
        carPic.draw();
    }
    //picture = new Picture(STARTING_X, STARTING_Y, "Resources/catiaStreetStore.PNG"); //Create Character model on the screen

    public void setBackground(Background background) {
        this.background = background;
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
        if (taxi) {
            //carPic.delete();
            //carPic = new Picture(posX, posY, carTiny);
            carPic.load(carTiny);
            //carPic.draw();
            taxi = false;
        }
        else if (!taxi) {
            changeToTaxi();
            taxi = true;
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


    public void changeToFerrari() {
        if (taxi) {
            carPic.load(ferrari);
            taxi = false;
            godMode = true;
        }
        else if (!taxi) {
            changeToTaxi();
        }
    }
    public void changeToSnail() {
        if (taxi) {
            carPic.load(snail);
            Game.setSPEED(Speed.FAST.getSpeedValue());////////////////////////////////////////////////////////
            taxi = false;
        } else if (!taxi) {
            changeToTaxi();
        }
    }

    public boolean getGodMode() {
        return godMode;
    }
    public Picture getCarPic(){
        return carPic;
    }

    public void changeToTaxi () {
        carPic.load(carRight);
        taxi = true;
        godMode = false;
        Game.setSPEED(10);
        }
    }

