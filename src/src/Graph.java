package src;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Graph {

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    private List<Node> nodes;

    private List<DijkstraNode> dijkstraGraph = null;

    public List<Node> getNodes() {
        return nodes;
    }

    public void printGraph() {
        nodes.forEach(System.out::println);
    }

    public int getShortestPath(Node source, Node destination) {
        generateDijkstraGraph(source);
        var dijkstraSource = findNode(source);
        var dijkstraDestination = findNode(destination);

        printResult(dijkstraSource, dijkstraDestination);

        return dijkstraDestination.getDistance();
    }

    public void printAllShortestPath(Node source) {
        generateDijkstraGraph(source);
        var dijkstraSource = findNode(source);
        nodes.forEach(n -> {
            var dijkstraDestination = findNode(n);
            if(dijkstraSource != dijkstraDestination) {
                printResult(dijkstraSource, dijkstraDestination);
            }
        });
    }

    private void generateDijkstraGraph(Node source) {
        dijkstraGraph = initializeDistance(source);
        exploreGraph(source);
    }

    private List<DijkstraNode> initializeDistance(Node source) {
        return nodes.stream().map(node -> {
            if (node != source) {
                return new DijkstraNode(node, -1);
            }
            var dijkstraSource = new DijkstraNode(node, 0);
            dijkstraSource.setExplored(true);
            return dijkstraSource;
        }).toList();
    }

    private void exploreGraph(Node source) {
        var dijkstraNext = findNode(source);

        while (true) {
            updateDistance(dijkstraNext);
            var optionalDijkstraNext = getDijkstraNext(dijkstraNext);
            if (optionalDijkstraNext.isEmpty()) {
                break;
            }
            dijkstraNext = optionalDijkstraNext.orElseThrow();
            dijkstraNext.setExplored(true);
        }
    }

    private Optional<DijkstraNode> getDijkstraNext(DijkstraNode dijkstraNow) {
        return dijkstraNow.getNode().getNodes().keySet().stream().map(this::findNode).filter(dijkstraNode -> !dijkstraNode.isExplored()).min(Comparator.comparing(DijkstraNode::getDistance));
    }

    private void updateDistance(DijkstraNode dijkstraSource) {
        dijkstraSource.getNode().getNodes().forEach((node, edge) -> {
            var dijkstraNode = findNode(node);
            if (shouldUpdateDistance(dijkstraSource, edge, dijkstraNode)) {
                dijkstraNode.setPreviousDijkstraNode(dijkstraSource);
                dijkstraNode.setDistance(edge.getDistance() + dijkstraSource.getDistance());
            }
        });
    }

    private boolean shouldUpdateDistance(DijkstraNode dijkstraSource, Edge edge, DijkstraNode dijkstraNode) {
        return !dijkstraNode.isExplored() && ((edge.getDistance() + dijkstraSource.getDistance()) < dijkstraNode.getDistance() || dijkstraNode.getDistance() == -1);
    }

    private DijkstraNode findNode(Node node) {
        return dijkstraGraph.stream().filter(dn -> dn.getNode() == node).findFirst().orElseThrow();
    }

    private void printResult(DijkstraNode dijkstraSource, DijkstraNode dijkstraDestination) {
        if (dijkstraDestination.getDistance() != -1) {
            StringBuffer buffer = getSmallestPathDescription(dijkstraSource, dijkstraDestination);
            System.out.println(dijkstraSource.getNode().getName() + " and " + dijkstraDestination.getNode().getName() + ": distance:"  + dijkstraDestination.getDistance() + " path:" + buffer);
        } else {
            System.out.println(dijkstraSource.getNode().getName() + " and " + dijkstraDestination.getNode().getName() + ": there is no path" );
        }
    }

    private StringBuffer getSmallestPathDescription(DijkstraNode dijkstraSource, DijkstraNode dijkstraDestination) {
        var backwardsPath = dijkstraDestination;
        Stack<String> bestPath = new Stack<>();
        do {
            //TODO: it crashes trying to printing source and source distance
            bestPath.push("(" + backwardsPath.getPreviousDijkstraNode().getNode().getName() + ") - " + backwardsPath.getPreviousDijkstraNode().getNode().getNodes().get(backwardsPath.getNode()).getDistance() + " - ");
            backwardsPath = backwardsPath.getPreviousDijkstraNode();
        } while (backwardsPath != dijkstraSource);
        StringBuffer buffer = new StringBuffer();
        while (!bestPath.isEmpty()) {
            buffer.append(bestPath.pop());
        }
        buffer.append("(").append(dijkstraDestination.getNode().getName()).append(")");
        return buffer;
    }
}
