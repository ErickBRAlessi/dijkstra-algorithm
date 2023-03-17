package src;

public class DijkstraNode {

    public DijkstraNode(Node node, int distance){
        this.node = node;
        this.distance = distance;
    }

    private Node node;
    private int distance;
    private boolean explored;
    private DijkstraNode previousDijkstraNode = this;

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

    public boolean isExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public DijkstraNode getPreviousDijkstraNode() {
        return previousDijkstraNode;
    }

    public void setPreviousDijkstraNode(DijkstraNode previousDijkstraNode) {
        this.previousDijkstraNode = previousDijkstraNode;
    }

    @Override
    public String toString() {
        return "DijkstraNode{" +
                "node=" + node +
                ", distance=" + distance +
                ", explored=" + explored +
                '}';
    }
}
