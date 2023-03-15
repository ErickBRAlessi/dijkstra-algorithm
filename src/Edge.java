public class Edge {

    private long distance;

    public Edge(){
        setRandomDistance();
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public void setRandomDistance(){
        this.distance = (long) (Math.random() * 9) + 1L;
    }
}
