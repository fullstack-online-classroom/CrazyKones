package game;

public enum Speed {
    FAST(20),
    MEDIUM(11),
    SLOW(3);
    private int speedValue;

    Speed(int speedValue){
        this.speedValue = speedValue;
    }

    public int getSpeedValue(){
        return speedValue;
    }
}
