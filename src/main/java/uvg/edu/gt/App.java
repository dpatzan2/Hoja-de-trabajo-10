package uvg.edu.gt;
import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        try {
            Graph graph = GraphReader.readGraphFromFile("./grafo_text.txt");

            GraphService graphService = new GraphService(graph);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Find shortest path between two cities");
                System.out.println("2. Find center of the graph");
                System.out.println("3. Modify graph");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        graphService.modifyGraph("Guatemala", "Antigua", 30);
                        System.out.print("Enter source city: ");
                        String source = scanner.next();
                        System.out.print("Enter destination city: ");
                        String destination = scanner.next();
                        String shortestPath = graphService.findShortestPath(source, destination);
                        System.out.println(shortestPath);
                        break;
                    case 2:
                        String center = graphService.findGraphCenter();
                        System.out.println(center);
                        break;
                    case 3:
                        System.out.print("Enter source city: ");
                        source = scanner.next();
                        System.out.print("Enter destination city: ");
                        destination = scanner.next();
                        System.out.print("Enter weight (0 to remove edge): ");
                        int weight = scanner.nextInt();
                        graphService.modifyGraph(source, destination, weight);
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice!");
                }

                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printGraph() {
        System.out.println("Graph adjacency matrix:");
        int numVertices = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                Object[][] adjacencyMatrix = new Object[0][];
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
