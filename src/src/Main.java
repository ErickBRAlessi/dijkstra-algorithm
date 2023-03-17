package src;

public class Main {

    public static void main(String[] args) {

        GraphFactory graphFactory = new GraphFactory(10000, 1);
        var init = System.currentTimeMillis();
        var graph = graphFactory.getGraph();
        //graph.printGraph();
        graph.getShortestPath(graph.getNodes().get(0), graph.getNodes().get(9999));
        System.out.println("Tempo total :" + (System.currentTimeMillis() - init)/1000 );
        //graph.printGraph();

    }


}