package src;

public class Main {

    public static void main(String[] args) {

        GraphFactory graphFactory = new GraphFactory(1000, 0.5f);
        var graph = graphFactory.getGraph();
        System.out.println("Graph Created");
        var init = System.currentTimeMillis();
        //graph.printGraph();
        graph.printAllShortestPath(graph.getNodes().get(0));
        System.out.println("Tempo total :" + (System.currentTimeMillis() - init)/1000 );
        //graph.printGraph();

    }


}