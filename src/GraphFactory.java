import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class GraphFactory {

    private int linkRate = 0;
    private int totalNodes = 0;

    public GraphFactory(int totalNodes, int linkRate){
        this.totalNodes = totalNodes;
        this.linkRate = linkRate;
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
        var numNodes = totalNodes;
        for(var i = 0; i < numNodes; i++){
            var node = nodes.get(i);
            while(node.getNodes().size() < calculateMinimumLinkedNode()){
                var randomGroup = new HashSet<Node>();
                var rng= (new Random()).nextInt(numNodes);
                if(i != rng){
                    randomGroup.add(nodes.get(rng));
                }
                randomGroup.forEach(node::twoHandLinkNode);
            }
        }
    }

    private int calculateMinimumLinkedNode(){
        return (totalNodes * linkRate / 100) - 1;
    }


}
