package game;

public enum Speed {
    FAST(20),
    MEDIUM(10),
    SLOW(2);
    private int speedValue;

    Speed(int speedValue){
        this.speedValue = speedValue;
    }

    public int getSpeedValue(){
        return speedValue;
    }
}
