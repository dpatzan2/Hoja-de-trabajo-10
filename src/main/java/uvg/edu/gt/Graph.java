package uvg.edu.gt;

import java.util.Map;
import java.util.HashMap;

public class Graph {
    private int numVertices;
    private int[][] adjacencyMatrix;
    private Map<String, Integer> cityIndex;
    private int[][] shortestPaths;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyMatrix = new int[numVertices][numVertices];
        this.cityIndex = new HashMap<>();
        this.shortestPaths = new int[numVertices][numVertices]; // Initialize shortestPaths array
    }

    public void addEdge(String source, String destination, int weight) {
        int srcIndex = cityIndex.get(source);
        int destIndex = cityIndex.get(destination);
        adjacencyMatrix[srcIndex][destIndex] = weight;
    }

    public void removeEdge(String source, String destination) {
        int srcIndex = cityIndex.get(source);
        int destIndex = cityIndex.get(destination);
        adjacencyMatrix[srcIndex][destIndex] = 0;
    }

    public void floyd() {
        int[][] dist = new int[numVertices][numVertices];

        // Initialize dist array with initial distances
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (adjacencyMatrix[i][j] != 0) {
                    dist[i][j] = adjacencyMatrix[i][j];
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // Calculate shortest paths using Floyd-Warshall algorithm
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                if (dist[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = 0; j < numVertices; j++) {
                    if (dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        shortestPaths = dist;
    }


    public String findCenter() {
        int[] eccentricity = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            int maxDist = 0;
            for (int j = 0; j < numVertices; j++) {
                if (shortestPaths[i][j] != Integer.MAX_VALUE &&
                        shortestPaths[i][j] > maxDist) {
                    maxDist = shortestPaths[i][j];
                }
            }
            eccentricity[i] = maxDist;
        }

        int minEccentricity = Integer.MAX_VALUE;
        int centerIndex = -1;
        for (int i = 0; i < numVertices; i++) {
            if (eccentricity[i] < minEccentricity) {
                minEccentricity = eccentricity[i];
                centerIndex = i;
            }
        }

        String center = null;
        for (Map.Entry<String, Integer> entry : cityIndex.entrySet()) {
            if (entry.getValue() == centerIndex) {
                center = entry.getKey();
                break;
            }
        }

        return center;
    }

    public void printShortestPaths() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j) {
                    String source = getCity(i);
                    String destination = getCity(j);
                    int distance = shortestPaths[i][j];
                    System.out.println("Shortest path from " + source + " to " +
                            destination + ": " + distance);
                }
            }
        }
    }

    public String getCity(int index) {
        for (Map.Entry<String, Integer> entry : cityIndex.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return null;
    }

    public int getCityIndex(String city) {
        return cityIndex.getOrDefault(city, -1);
    }

    public void addCity(String city, int index) {
        cityIndex.put(city, index);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int[][] getShortestPaths() {
        return shortestPaths;
    }

    public void setShortestPaths(int[][] shortestPaths) {
        this.shortestPaths = shortestPaths;
    }
}