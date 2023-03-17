package src;

import java.util.Random;

public class Edge {

    private int distance;

    public Edge(){
        setRandomDistance();
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setRandomDistance(){
        this.distance = (new Random()).nextInt(1, 10);
    }
}
