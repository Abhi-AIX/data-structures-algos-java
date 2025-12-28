## What is a Graph?

A **graph** is a data structure that models relationships. It consists of **nodes** (or **vertices**) which represent entities, and **edges**, which represent the connections between those entities.

### Why Graphs are Crucial in Interviews/CP:
- **Modeling Real-world Scenarios**: Social networks, computer networks, road systems, dependencies (`A` must finish before `B`), and state transitions can all be modeled as graphs.
- **Algorithmic Playground**: They are the basis for many classic algorithms: traversals (BFS, DFS), shortest path (Dijkstra's, Bellman-Ford), minimum spanning trees (Prim's, Kruskal's), and flow networks.
- **Problem Transformation**: Many problems that don't initially look like graph problems can be transformed into one. For example, a word ladder puzzle can be modeled as finding the shortest path in a graph of words.

---

## Core Components: The Language of Graphs

Let `G = (V, E)` be a graph.
- `V`: The set of vertices (nodes). We use `|V|` or simply `V` to denote the number of vertices.
- `E`: The set of edges. We use `|E|` or simply `E` to denote the number of edges.

### 1. Nodes/Vertices
A node represents an object, a state, or a point of interest.
- **Example**: In a map, a city is a node. In a dependency graph, a task is a node.

### 2. Edges
An edge `(u, v)` represents a connection or relationship between two nodes, `u` and `v`.
- **Example**: In a map, a road between two cities is an edge. In a dependency graph, an edge from `A` to `B` means `A` depends on `B`.

---

## How to Represent a Graph in Code

This is the most critical part for implementation. The choice of representation directly impacts the time and space complexity of your solution.

### 1. Adjacency List (Most Common in CP)

An array of lists. The index of the array represents a vertex, and the list at that index stores all its neighbors.

**Best for**: Sparse graphs (where `E` is much smaller than `V²`). This is the default choice in competitive programming.

**Java Implementation:**
```
// V = number of vertices
List<List<Integer>> adj = new ArrayList<>(V);
for (int i = 0; i < V; i++) {
    adj.add(new ArrayList<>());
}

// Add an edge from u to v (for undirected graph, add the reverse edge too)
adj.get(u).add(v);
adj.get(v).add(u); // For an undirected graph
```

**Complexity Analysis:**
- **Space**: `O(V + E)` - The most space-efficient for sparse graphs.
- **Add Edge**: `O(1)`
- **Check if edge (u, v) exists**: `O(degree(u))` or `O(k)`, where `k` is the number of `u`'s neighbors.
- **Iterate over neighbors of u**: `O(degree(u))` or `O(k)` - Very efficient.

### 2. Adjacency Matrix

A 2D `V x V` matrix where `matrix[u][v] = 1` (or a weight) if an edge exists from `u` to `v`, and `0` otherwise.

**Best for**: Dense graphs (where `E` is close to `V²`) or when you need `O(1)` lookup to check for an edge.

**Java Implementation:**
```
// V = number of vertices
int[][] adjMatrix = new int[V][V];

// Add an edge from u to v with weight 1
adjMatrix[u][v] = 1;
adjMatrix[v][u] = 1; // For an undirected graph
```

**Complexity Analysis:**
- **Space**: `O(V²)` - Can be prohibitive for large `V` (e.g., `V > 5000`).
- **Add Edge**: `O(1)`
- **Check if edge (u, v) exists**: `O(1)` - Its main advantage.
- **Iterate over neighbors of u**: `O(V)` - Inefficient for sparse graphs.

### 3. Edge List

A simple list of all the edges in the graph.

**Best for**: Algorithms that need to process all edges together, like Kruskal's MST algorithm or when the graph structure is static and you just need to iterate through all edges.

**Java Implementation:**
```
class Edge {
    int u, v, weight;
    // constructor
}

List<Edge> edgeList = new ArrayList<>();
edgeList.add(new Edge(u, v, w));
```

**Complexity Analysis:**
- **Space**: `O(E)`
- **Add Edge**: `O(1)`
- **Check if edge (u, v) exists**: `O(E)` - Very inefficient.
- **Iterate over neighbors of u**: `O(E)` - Very inefficient.

### Representation Trade-offs: Quick Summary

| Operation                 | Adjacency List | Adjacency Matrix | Edge List | When to Use?                               |
|---------------------------|----------------|------------------|-----------|--------------------------------------------|
| **Space Complexity**      | `O(V + E)`     | `O(V²)`          | `O(E)`    | **Adj. List** for sparse, **Matrix** for dense. |
| **Add Edge**              | `O(1)`         | `O(1)`           | `O(1)`    | All are fast.                              |
| **Check Edge (u, v)**     | `O(degree(u))` | `O(1)`           | `O(E)`    | **Matrix** is fastest.                     |
| **Get Neighbors of u**    | `O(degree(u))` | `O(V)`           | `O(E)`    | **Adj. List** is fastest.                  |
| **Typical Use Case**      | Default for CP | Dense graphs     | Kruskal's | **Adjacency List is your go-to.**          |

---

## Types of Graphs

### Directed Graphs (Digraphs)

Edges have a direction. An edge `(u, v)` means you can go from `u` to `v`, but not necessarily from `v` to `u`.

**CP Context**:
- **Models**: Prerequisite tasks (`A` must be done before `B`), one-way streets, web page links.
- **Algorithms**: Topological Sort is only possible on Directed Acyclic Graphs (DAGs). Cycle detection is a common problem.

### Undirected Graphs

Edges are bidirectional. An edge `(u, v)` means you can traverse from `u` to `v` AND from `v` to `u`.

**CP Context**:
- **Models**: Computer networks, social network friendships, any two-way connection.
- **Algorithms**: Connectivity problems (e.g., finding connected components, bridges, articulation points) are common.

### Weighted Graphs

Edges have an associated **weight** or **cost**. This is crucial for optimization problems.

**CP Context**:
- **Models**: Road networks with distances/tolls, network packets with latency, puzzles with costs for moves.
- **Algorithms**: Shortest path algorithms (Dijkstra's, Bellman-Ford, Floyd-Warshall) and Minimum Spanning Tree (MST) algorithms (Prim's, Kruskal's) operate on weighted graphs.

---

## Key Graph Properties

### Paths

A **path** is a sequence of vertices connected by edges. Finding if a path exists, the shortest path, or the longest path are all classic CP problems.
- **Path Length**: Can be the number of edges (unweighted) or the sum of edge weights (weighted).

### Cycles

A **cycle** is a path that starts and ends at the same vertex.
- **Cyclic Graph**: Contains at least one cycle.
- **Acyclic Graph**: Contains no cycles.

### Connectivity

- **Connected Components (Undirected)**: A subgraph where every vertex is reachable from every other. Finding the number of connected components is a classic application of BFS/DFS.
  - **Problem Example**: "Given a set of friendships, how many distinct social circles are there?"
- **Strongly Connected Components (SCCs) (Directed)**: A subgraph where for any two vertices `u` and `v`, there is a path from `u` to `v` AND from `v` to `u`.
  - **Problem Example**: "If you can travel between any two cities in a group, they form a single region. How many regions are there?" Solved with Tarjan's or Kosaraju's algorithm.

### Degrees of Vertices

The number of edges connected to a vertex.
- **Undirected Graph**: **Degree** is the number of neighbors.
  - The **Handshaking Lemma** (`Sum of degrees = 2 * E`) is a useful property.
- **Directed Graph**:
  - **In-Degree**: Number of incoming edges. Useful for topological sorting (Kahn's algorithm starts with in-degree 0 nodes).
  - **Out-Degree**: Number of outgoing edges.

---

## Common Graph Algorithms: 

This is a quick overview. Each of these is a deep topic.

| Algorithm Family          | Algorithms                               | Common Problem Types                                     |
|---------------------------|------------------------------------------|----------------------------------------------------------|
| **Graph Traversal**       | Breadth-First Search (BFS), Depth-First Search (DFS) | Finding paths, connectivity, cycle detection, flood fill. |
| **Shortest Path**         | Dijkstra's, Bellman-Ford, Floyd-Warshall, SPFA | Finding the cheapest/fastest route from A to B.          |
| **Minimum Spanning Tree** | Prim's Algorithm, Kruskal's Algorithm    | Connecting all nodes with minimum total edge cost.       |
| **Topological Sorting**   | Kahn's Algorithm (BFS-based), DFS-based  | Scheduling tasks with dependencies.                      |
| **Strong Connectivity**   | Tarjan's Algorithm, Kosaraju's Algorithm | Decomposing a directed graph into strongly connected components. |

