# Introduction to implementation of Graphs using ArrayList and Adjacency List

Welcome! This guide will help you understand graphs from scratch. Don't worry if it seems confusing at first - graphs are actually quite simple once you see them in action!

---

## 📚 Table of Contents
1. [What is a Graph?](#what-is-a-graph)
2. [Why Use ArrayList?](#why-use-arraylist)
3. [What is an Adjacency List?](#what-is-an-adjacency-list)
4. [How to Use the Code](#how-to-use-the-code)
5. [Step-by-Step Examples](#step-by-step-examples)

---

## What is a Graph?

### Simple Definition
A **graph** is just a way to show connections between things.

### Real-World Examples:
- **Social Network**: People (vertices) and friendships (edges)
- **City Map**: Cities (vertices) and roads (edges)
- **Website**: Web pages (vertices) and links (edges)
- **Family Tree**: People (vertices) and relationships (edges)

### Two Main Parts:
1. **VERTICES** (also called nodes or points)
   - These are the "things" in your graph
   - Example: Cities on a map
   
2. **EDGES** (also called connections or links)
   - These connect the vertices
   - Example: Roads between cities

### Visual Example:
```
     0 ---- 1
     |      |
     |      |
     3 ---- 2

Vertices: 0, 1, 2, 3 (four cities)
Edges: 0-1, 1-2, 2-3, 3-0 (roads connecting them)
```

---

## Why Use ArrayList?

### The Question: Why not use regular arrays?

Let's compare:

### ❌ Regular Arrays (Problems):
```java
// Fixed size - can't grow!
int[] neighbors = new int[10];  

// What if vertex 0 has 3 neighbors but vertex 1 has 50?
// You waste space OR run out of space!
```

### ✅ ArrayList (Solutions):
```java
// Can grow dynamically!
ArrayList<Integer> neighbors = new ArrayList<>();

// Add as many as you need:
neighbors.add(1);
neighbors.add(2);
neighbors.add(3);
// ... can keep adding!
```

### Why ArrayList is Better for Graphs:

1. **Dynamic Size**
   - Vertices can have different numbers of neighbors
   - City A might connect to 2 cities, City B to 10 cities
   - ArrayList handles this automatically!

2. **Easy to Use**
   ```java
   list.add(5);           // Add element
   list.get(0);           // Get element
   list.size();           // Get size
   list.remove(2);        // Remove element
   ```

3. **No Wasted Space**
   - Only uses memory for what you actually store
   - Arrays need to allocate maximum possible size upfront

4. **Safer**
   - No worrying about array index out of bounds
   - ArrayList grows automatically when needed

---

## What is an Adjacency List?

### Simple Explanation
An **adjacency list** stores all neighbors of each vertex in a list.

### Think of it Like a Phone Book:
```
Person 0's contacts: [1, 3]       (knows person 1 and 3)
Person 1's contacts: [0, 2]       (knows person 0 and 2)
Person 2's contacts: [1, 3]       (knows person 1 and 3)
Person 3's contacts: [0, 2]       (knows person 0 and 2)
```

### In Code:
```java
List<List<Integer>> adj = new ArrayList<>();

// For a graph with 4 vertices:
adj.add(new ArrayList<>());  // vertex 0's neighbors
adj.add(new ArrayList<>());  // vertex 1's neighbors
adj.add(new ArrayList<>());  // vertex 2's neighbors
adj.add(new ArrayList<>());  // vertex 3's neighbors

// Add edge 0-1:
adj.get(0).add(1);  // 1 is neighbor of 0
adj.get(1).add(0);  // 0 is neighbor of 1
```

### Visual Representation:
```
Graph:           Adjacency List:
  0---1          0: [1, 3]
  |   |          1: [0, 2]
  3---2          2: [1, 3]
                 3: [0, 2]
```

### Why "List of Lists"?
- **Outer List**: One slot for each vertex (0, 1, 2, 3...)
- **Inner Lists**: Neighbors of that vertex

```java
List<List<Integer>> adj
     ^    ^
     |    |
Outer  Inner
List   Lists
```

---

## How to Use the Code

### 1. Create a Graph
```java
// Create a graph with 4 vertices (numbered 0, 1, 2, 3)
GraphListImpl graph = new GraphListImpl(4);
```

### 2. Add Edges (Connections)
```java
// Connect vertex 0 and 1 (undirected - both ways)
graph.addEdge(0, 1);

// Connect vertex 1 and 2
graph.addEdge(1, 2);

// One-way connection: 2 -> 3 only
graph.addEdge(2, 3, false);  // false = directed (one-way)
```

### 3. Find Neighbors
```java
// Who is vertex 0 connected to?
List<Integer> neighbors = graph.neighbors(0);
System.out.println(neighbors);  // Prints: [1]
```

### 4. Print the Graph
```java
System.out.println(graph);
// Shows all vertices and their connections
```

---

## Step-by-Step Examples

### Example 1: Simple Friendship Network

```java
// Create graph with 3 people
GraphListImpl friends = new GraphListImpl(3);

// Alice (0) is friends with Bob (1)
friends.addEdge(0, 1);

// Bob (1) is friends with Charlie (2)
friends.addEdge(1, 2);

// Who are Bob's friends?
System.out.println(friends.neighbors(1));  // [0, 2] (Alice and Charlie)
```

### Example 2: City Road Map

```java
// 4 cities
GraphListImpl cities = new GraphListImpl(4);

// Two-way roads
cities.addEdge(0, 1);  // City A <-> City B
cities.addEdge(1, 2);  // City B <-> City C
cities.addEdge(2, 3);  // City C <-> City D

// One-way road
cities.addEdge(3, 0, false);  // City D -> City A (one way only)

// From City B, where can you go?
System.out.println(cities.neighbors(1));  // [0, 2] (to A or C)
```

### Example 3: Step-by-Step Execution

```java
// Start with empty graph (4 vertices)
GraphListImpl g = new GraphListImpl(4);
// Internal state: [[],[],[],[]]

// Add edge 0-1
g.addEdge(0, 1);
// Internal state: [[1],[0],[],[]]
//                  ↑   ↑
//                  |   1's neighbors include 0
//                  0's neighbors include 1

// Add edge 1-2
g.addEdge(1, 2);
// Internal state: [[1],[0,2],[1],[]]

// Add edge 2-3 (directed: 2->3 only)
g.addEdge(2, 3, false);
// Internal state: [[1],[0,2],[1,3],[]]
//                                ↑
//                                Only 2->3, not 3->2
```

---

## 🎓 Key Concepts Summary

### Undirected vs Directed Edges

**Undirected** (two-way):
```java
graph.addEdge(0, 1);  // or graph.addEdge(0, 1, true);
// Creates: 0 <-> 1 (can go both ways)
```

**Directed** (one-way):
```java
graph.addEdge(0, 1, false);
// Creates: 0 -> 1 (can only go from 0 to 1)
```

### When to Use Each?

| Type | Example |
|------|---------|
| Undirected | Friendships (if A is friend of B, then B is friend of A) |
| Undirected | Two-way roads |
| Directed | Following on Twitter (A follows B doesn't mean B follows A) |
| Directed | One-way streets |

---

## 🚀 Try It Yourself!

### Compile and Run:
```powershell
# Compile
javac -d out -sourcepath src src\graphs\problems\GraphListImpl.java src\graphs\problems\GraphListDemo.java

# Run the demo
java -cp out graphs.problems.GraphListDemo
```

### Experiment:
1. Change the number of vertices
2. Add different edges
3. Try directed vs undirected
4. Print neighbors of different vertices

---

## 💡 Common Questions

**Q: Why start counting from 0?**
A: In programming, we usually count from 0. So 4 vertices are numbered: 0, 1, 2, 3

**Q: Can a vertex connect to itself?**
A: Yes! `graph.addEdge(0, 0)` creates a "self-loop"

**Q: Can I have multiple edges between same vertices?**
A: In this implementation, yes. If you call `addEdge(0,1)` twice, vertex 0's neighbor list will have `[1, 1]`

**Q: What happens if I try to add edge with invalid vertex number?**
A: The method returns `false` and doesn't add the edge

