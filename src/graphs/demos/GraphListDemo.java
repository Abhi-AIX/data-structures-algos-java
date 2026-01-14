package graphs.demos;

import graphs.implementations.GraphListImpl;
import java.util.List;

/**
 * BEGINNER DEMO: How to use GraphListImpl
 *
 * This shows a simple example of creating a graph and using it.
 * Perfect for your first day learning graphs!
 */
public class GraphListDemo {
    public static void main(String[] args) {

        System.out.println("=== GRAPH TUTORIAL FOR BEGINNERS ===\n");

        // STEP 1: Create a graph with 4 vertices (0, 1, 2, 3)
        System.out.println("STEP 1: Creating a graph with 4 vertices (0, 1, 2, 3)");
        GraphListImpl graph = new GraphListImpl(4);
        System.out.println(graph);

        // STEP 2: Add some edges (connections between vertices)
        System.out.println("\nSTEP 2: Adding edges (connections)");
        System.out.println("Let's connect some vertices:");

        // Connect vertex 0 to vertex 1 (undirected = both ways)
        System.out.println("  - Connecting 0 and 1 (like a two-way road)");
        graph.addEdge(0, 1);

        // Connect vertex 1 to vertex 2
        System.out.println("  - Connecting 1 and 2");
        graph.addEdge(1, 2);

        // Connect vertex 2 to vertex 3
        System.out.println("  - Connecting 2 and 3");
        graph.addEdge(2, 3);

        // Connect vertex 3 back to vertex 0 (makes a cycle!)
        System.out.println("  - Connecting 3 and 0 (this creates a cycle!)");
        graph.addEdge(3, 0);

        System.out.println("\nAfter adding edges:");
        System.out.println(graph);

        // STEP 3: Find neighbors of a vertex
        System.out.println("\nSTEP 3: Finding neighbors");
        System.out.println("Who is vertex 0 connected to?");
        List<Integer> neighborsOf0 = graph.neighbors(0);
        System.out.println("  Neighbors of vertex 0: " + neighborsOf0);

        System.out.println("\nWho is vertex 2 connected to?");
        List<Integer> neighborsOf2 = graph.neighbors(2);
        System.out.println("  Neighbors of vertex 2: " + neighborsOf2);

        // STEP 4: Example of directed edge (one-way connection)
        System.out.println("\n\nSTEP 4: Adding a DIRECTED edge (one-way)");
        System.out.println("Let's add a one-way connection from 1 to 3");
        System.out.println("(Like a one-way street)");
        graph.addEdge(1, 3, false); // false = directed (one-way only)

        System.out.println("\nAfter adding directed edge 1->3:");
        System.out.println("  Neighbors of 1: " + graph.neighbors(1) + " (has 3)");
        System.out.println("  Neighbors of 3: " + graph.neighbors(3) + " (doesn't have extra 1)");

        // STEP 5: Real-world analogy
        System.out.println("\n\n=== REAL WORLD ANALOGY ===");
        System.out.println("Think of this graph as a simple city map:");
        System.out.println("  Vertex 0 = City A");
        System.out.println("  Vertex 1 = City B");
        System.out.println("  Vertex 2 = City C");
        System.out.println("  Vertex 3 = City D");
        System.out.println("\nRoads (edges) connect:");
        System.out.println("  A <-> B <-> C <-> D <-> A");
        System.out.println("  Plus a one-way road B -> D");
        System.out.println("\nFinal graph:");
        System.out.println(graph);
    }
}

