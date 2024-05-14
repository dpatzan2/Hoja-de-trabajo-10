package uvg.edu.gt;

public class GraphService {
    private Graph graph;

    public GraphService(Graph graph) {
        this.graph = graph;

    }

    public void modifyGraph(String source, String destination, int weight) {
        if (weight == 0) {
            graph.removeEdge(source, destination);
        } else {
            graph.addEdge(source, destination, weight);
        }
        graph.floyd();
    }

    public String findShortestPath(String source, String destination) {
        int srcIndex = graph.getCityIndex(source);
        int destIndex = graph.getCityIndex(destination);
        int distance = graph.getShortestPaths()[srcIndex][destIndex];
        return "Shortest path from " + source + " to " + destination + ": " + distance;
    }

    public String findGraphCenter() {
        return "Center of the graph: " + graph.findCenter();
    }
}
