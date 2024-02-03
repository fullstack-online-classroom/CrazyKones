package game;

import java.util.ArrayList;
import java.util.List;

public class ObstacleFactory {

    public List<Obstacle> obstacles = new ArrayList<>();

    public ObstacleFactory() {

    }

    public void addKone(){
        Kone kone = new Kone();
        obstacles.add(kone);
    }

    public void removeObstacles(){
        for(int i = (obstacles.size() - 1); i >= 0 ; i--) {
            //obstacles.get(i).getKonePic().delete();
            obstacles.remove(i);
        }
    }

}
