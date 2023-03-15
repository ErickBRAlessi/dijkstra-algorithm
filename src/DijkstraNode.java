public class DijkstraNode {

    public DijkstraNode(Node node, int distance){
        this.node = node;
        this.distance = distance;
    }

    private Node node;
    private int distance;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
