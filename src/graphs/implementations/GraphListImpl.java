package graphs.implementations;

import java.util.ArrayList;
import java.util.List;

/**
 * BEGINNER-FRIENDLY GRAPH IMPLEMENTATION using ArrayList
 *
 * WHAT IS A GRAPH?
 * ================
 * A graph is a collection of:
 * - VERTICES (also called nodes): Think of them as cities on a map
 * - EDGES (also called connections): Roads connecting the cities
 *
 * Example: If you have 4 cities numbered 0, 1, 2, 3
 * And roads connecting: 0-1, 1-2, 2-3, 3-0
 * That's a graph!
 *
 * WHY USE ARRAYLIST (not arrays)?
 * ===============================
 * 1. DYNAMIC SIZE: ArrayList can grow/shrink. Arrays are fixed size.
 * 2. EASY TO USE: ArrayList has .add(), .get(), .size() methods
 * 3. FLEXIBLE: Each vertex can have different number of neighbors
 *
 * Example: Vertex 0 might connect to 3 cities, vertex 1 to only 1 city.
 *          With ArrayList, this is easy! With arrays, you'd waste space.
 *
 * WHAT IS AN ADJACENCY LIST?
 * ==========================
 * It's a way to store a graph using a "list of lists":
 * - Position 0 in the list = neighbors of vertex 0
 * - Position 1 in the list = neighbors of vertex 1
 * - And so on...
 *
 * Example:
 * If vertex 0 connects to vertices 1 and 2
 * If vertex 1 connects to vertex 2
 * If vertex 2 connects to nobody
 *
 * Then adj looks like:
 * adj[0] = [1, 2]
 * adj[1] = [2]
 * adj[2] = []
 */
public class GraphListImpl {

    // The main data structure: A List of Lists
    // Outer List: Each index represents a vertex (0, 1, 2, 3, ...)
    // Inner List: Contains all neighbors of that vertex
    private final List<List<Integer>> adj;

    /**
     * Create a graph with 'n' vertices.
     * Vertices will be numbered 0, 1, 2, ..., n-1
     *
     * Example: new GraphListImpl(4) creates vertices: 0, 1, 2, 3
     */
    public GraphListImpl(int n) {
        // Make sure n is not negative
        if (n < 0) n = 0;

        // Create the outer list (one slot for each vertex)
        adj = new ArrayList<>();

        // For each vertex, create an empty list of neighbors
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Now adj looks like: [[], [], [], []] for n=4
        // Each empty [] will store neighbors when we add edges
    }

    /**
     * How many vertices does this graph have?
     */
    public int vertices() {
        return adj.size();
    }

    /**
     * Add an EDGE (connection) between vertex u and vertex v
     *
     * Parameters:
     * - u: first vertex (like city A)
     * - v: second vertex (like city B)
     * - undirected: if true, creates a two-way road (u to v AND v to u)
     *               if false, creates a one-way road (only u to v)
     *
     * Returns: true if edge was added, false if u or v are invalid
     *
     * EXAMPLE:
     * addEdge(0, 1, true) means: connect vertex 0 and 1 (both ways)
     * After this: adj[0] will have 1 in its list
     *             adj[1] will have 0 in its list
     */
    public boolean addEdge(int u, int v, boolean undirected) {
        // Check if u and v are valid vertex numbers
        if (u < 0 || v < 0 || u >= vertices() || v >= vertices()) {
            return false; // Invalid! Can't add edge
        }

        // Add v to u's neighbor list (u -> v)
        adj.get(u).add(v);

        // If undirected, also add u to v's neighbor list (v -> u)
        if (undirected) {
            adj.get(v).add(u);
        }

        return true; // Success!
    }

    /**
     * Simpler version: adds an UNDIRECTED edge (two-way connection)
     * Most graphs you'll use at the beginning are undirected
     *
     * Example: addEdge(0, 2) connects vertex 0 and 2 (both ways)
     */
    public boolean addEdge(int u, int v) {
        return addEdge(u, v, true);
    }

    /**
     * Get all NEIGHBORS of vertex u
     * (All vertices that u is connected to)
     *
     * Example: If vertex 0 connects to vertices 1 and 2
     *          neighbors(0) returns [1, 2]
     */
    public List<Integer> neighbors(int u) {
        // Check if u is a valid vertex number
        if (u < 0 || u >= vertices()) {
            return new ArrayList<>(); // Return empty list if invalid
        }

        // Return a COPY of the neighbor list (so caller can't mess up our graph)
        return new ArrayList<>(adj.get(u));
    }

    /**
     * Convert graph to a readable string
     * Shows each vertex and its neighbors
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph with ").append(vertices()).append(" vertices:\n");

        // For each vertex, print its neighbors
        for (int i = 0; i < vertices(); i++) {
            sb.append("Vertex ").append(i).append(" connects to: ").append(adj.get(i)).append('\n');
        }

        return sb.toString();
    }
}

