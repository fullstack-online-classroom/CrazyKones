package com.codeforall.online.carcrash;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game(100, 25, 150);

        game.init();
        game.start();
    }
}
