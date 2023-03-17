package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {

    private String name;
    private Map<Node,Edge> nodes = new HashMap<>();

    public Node(String name){
        this.name = name;
    }

    public Node(String name, Map<Node,Edge> nodes){
        this.name = name;
        this.nodes = nodes;
    }

    public void twoHandLinkNode(Node node){
        Edge edge = new Edge();
        node.oneHandLinkNode(this, edge);
        this.oneHandLinkNode(node, edge);
    }

    public void oneHandLinkNode(Node node, Edge edge){
        nodes.put(node, edge);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Node, Edge> getNodes() {
        return nodes;
    }

    public void setNodes(Map<Node, Edge> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        List linkedNames = new ArrayList<String>();
       //nodes.forEach((node, edge) -> linkedNames.add("(" + node.name + "D" + edge.getDistance()+ ")"));
        nodes.forEach((node, edge) -> linkedNames.add("(" + name + ")-" + edge.getDistance() + "-(" + node.name + ")"));
        return "Node{" +
                "name='" + name + '\'' +
                "links='" + linkedNames +
                '}';
    }
}
