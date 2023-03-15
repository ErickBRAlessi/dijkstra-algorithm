import java.util.List;
import java.util.stream.Collectors;

public class Graph {

    public Graph(List<Node> nodes){
        this.nodes = nodes;
    }

    private List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }

    public void printGraph() {
        nodes.forEach(System.out::println);
    }

    public int getShortestPath(Node source, Node destination){
        return dijkstraAlgorithm(source, destination);
    }

    private int dijkstraAlgorithm(Node source, Node destination){
        var graph = initializeDistance(source);

        return -1;
    }

    private List<DijkstraNode> initializeDistance(Node source) {
        return nodes.stream().map(node -> {
            if (node != source) {
                return new DijkstraNode(node, -1);
            }
            return new DijkstraNode(node, 0);
        }).toList();
    }
}
