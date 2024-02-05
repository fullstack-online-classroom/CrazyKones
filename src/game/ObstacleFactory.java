package game;

import gameObjects.Client;
import gameObjects.Kone;
import gameObjects.Obstacle;

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
