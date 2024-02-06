package game;

import gameObjects.*;

import java.util.ArrayList;
import java.util.List;

public class ObstacleFactory {

    private List<Obstacle> obstacles = new ArrayList<>();

    public ObstacleFactory() {
    }

    public void addKone(){
        Kone kone = new Kone();
        obstacles.add(kone);
    }

    public void addFireKone(){
        FireKone fireKone = new FireKone();
        obstacles.add(fireKone);
    }

    public void addClient(){
        Client client = new Client();
        obstacles.add(client);
    }

    public void removeObstacles(){
        for(int i = (obstacles.size() - 1); i >= 0 ; i--) {
            obstacles.get(i).deletePic();
            obstacles.remove(i);
        }
    }

    public void removeObstacle(int i){
            obstacles.get(i).deletePic();
            obstacles.remove(i);
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

}
