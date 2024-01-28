package game;

public class Position {
    private double x;
    private double y;

    //private Background background;

    public Position(){
        x = Background.getWidth();
        y = Background.getHeight();
    }

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }
}
