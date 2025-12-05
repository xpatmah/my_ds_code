package com.my.ds.code.graphs.simple.contract;

import com.my.ds.code.graphs.simple.domain.Edge;
import com.my.ds.code.graphs.simple.domain.Vertex;

import java.util.List;
import java.util.Map;

/* --------------------
   Graph Interface
   -------------------- */
public interface GraphInterface<T> {
    Vertex<T> addVertex(T label);
    boolean removeVertex(Vertex<T> v);

    Edge<T> addEdge(Vertex<T> from, Vertex<T> to); // default weight=1.0
    Edge<T> addEdge(Vertex<T> from, Vertex<T> to, double weight);
    boolean removeEdge(Vertex<T> from, Vertex<T> to);

    boolean hasEdge(Vertex<T> from, Vertex<T> to);
    List<Vertex<T>> neighbors(Vertex<T> v);

    // Traversals and algorithms
    List<Vertex<T>> bfs(Vertex<T> start);
    List<Vertex<T>> dfsRecursive(Vertex<T> start);
    List<Vertex<T>> dfsIterative(Vertex<T> start);

    Map<Vertex<T>, Double> dijkstra(Vertex<T> source); // distances
    List<Vertex<T>> topologicalSort(); // only for directed graphs
    boolean detectCycle(); // directed cycle detection

    void display(); // readable view
    int size(); // number of vertices
    int edgeCount();
}