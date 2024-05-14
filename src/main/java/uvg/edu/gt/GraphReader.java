package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphReader {
    public static Graph readGraphFromFile(String filename) throws IOException {
        Set<String> cities = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String source = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 2));
                String destination = parts[parts.length - 2];
                cities.add(source);
                cities.add(destination);
            }
        }

        int numVertices = cities.size();
        Graph graph = new Graph(numVertices);

        int index = 0;
        for (String city : cities) {
            graph.addCity(city, index++);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String source = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 2));
                String destination = parts[parts.length - 2];
                int weight = Integer.parseInt(parts[parts.length - 1]);
                graph.addEdge(source, destination, weight);
            }
        }

        return graph;
    }
}
