import java.util.*;

public class Main {

    public static void main(String[] args) {
        GraphFactory graphFactory = new GraphFactory(10, 50);
        var graph = graphFactory.getGraph();
        graph.printGraph();
    }


}