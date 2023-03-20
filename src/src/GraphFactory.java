package src;

import java.util.*;

public class GraphFactory {

    private float linkRate = 0f;
    private int totalNodes = 0;

    public GraphFactory(int totalNodes, float linkRate){
        this.totalNodes = totalNodes;
        this.linkRate = Math.max(linkRate, 0f);
    }

    public Graph getGraph(){
        List<Node> nodes = generateNodes();
        randomlyLinkNodes(nodes);
        return new Graph(nodes);
    }

    private List<Node> generateNodes(){
        List<Node> nodes = new ArrayList<>();
        for(var i = 0; i < totalNodes; i++){
            nodes.add(new Node("N" + i));
        }
        return nodes;
    }


    private void randomlyLinkNodes(List<Node> nodes) {
        var minimumLinkedNode = calculateMinimumLinkedNode();
        for(var i = 0; i < totalNodes; i++){
            var nodeToBeLinked = nodes.get(i);
            while(nodeToBeLinked.getNodes().size() < minimumLinkedNode) {
                var rng = (new Random()).nextInt(totalNodes);
                if (i != rng && !nodeToBeLinked.isLinkedTo(nodes.get(rng))) {
                    nodeToBeLinked.twoHandLinkNode(nodes.get(rng));
                }
            }
        }
    }

    private int calculateMinimumLinkedNode(){
        var minimumLinkedNodes = (int) ((totalNodes * linkRate / 100f) - 1f);
        return Math.min(minimumLinkedNodes, (totalNodes - 1));
    }


}
