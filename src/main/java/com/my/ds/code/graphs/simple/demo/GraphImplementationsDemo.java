package com.my.ds.code.graphs.simple.demo;

import com.my.ds.code.graphs.simple.domain.Vertex;
import com.my.ds.code.graphs.simple.impl.AdjacencyListGraph;
import com.my.ds.code.graphs.simple.impl.AdjacencyMatrixGraph;

import java.util.Map;
import java.util.stream.Collectors;

/* --------------------
   Demo
   -------------------- */
public class GraphImplementationsDemo {
    public static void main(String[] args) {
        System.out.println("=== Adjacency List Graph (directed, weighted) Demo ===");
        AdjacencyListGraph<String> listGraph = new AdjacencyListGraph<>(true);
        Vertex<String> a = listGraph.addVertex("A");
        Vertex<String> b = listGraph.addVertex("B");
        Vertex<String> c = listGraph.addVertex("C");
        Vertex<String> d = listGraph.addVertex("D");

        listGraph.addEdge(a, b, 1.0);
        listGraph.addEdge(a, c, 4.0);
        listGraph.addEdge(b, c, 2.0);
        listGraph.addEdge(b, d, 7.0);
        listGraph.addEdge(c, d, 3.0);

        listGraph.display();
        System.out.println("BFS from A: " + listGraph.bfs(a));
        System.out.println("DFS recursive from A: " + listGraph.dfsRecursive(a));
        System.out.println("DFS iterative from A: " + listGraph.dfsIterative(a));
        System.out.println("Dijkstra from A: " + mapToString(listGraph.dijkstra(a)));
        System.out.println("Topological sort: " + listGraph.topologicalSort());
        System.out.println("Has cycle? " + listGraph.detectCycle());

        System.out.println("\n=== Adjacency Matrix Graph (undirected, weighted) Demo ===");
        AdjacencyMatrixGraph<String> matGraph = new AdjacencyMatrixGraph<>(false);
        Vertex<String> v1 = matGraph.addVertex("1");
        Vertex<String> v2 = matGraph.addVertex("2");
        Vertex<String> v3 = matGraph.addVertex("3");
        Vertex<String> v4 = matGraph.addVertex("4");

        matGraph.addEdge(v1, v2, 5.0);
        matGraph.addEdge(v2, v3, 6.0);
        matGraph.addEdge(v3, v4, 2.0);
        matGraph.addEdge(v4, v1, 1.0);

        matGraph.display();
        System.out.println("Neighbors of 2: " + matGraph.neighbors(v2));
        System.out.println("BFS from 1: " + matGraph.bfs(v1));
        System.out.println("DFS recursive from 1: " + matGraph.dfsRecursive(v1));
        System.out.println("Dijkstra from 1: " + mapToString(matGraph.dijkstra(v1)));
        System.out.println("Contains cycle? " + matGraph.detectCycle());

        System.out.println("\n--- Done demo ---");
    }

    private static <T> String mapToString(Map<Vertex<T>, Double> map) {
        return map.entrySet().stream()
            .map(e -> e.getKey() + ":" + (Double.isInfinite(e.getValue()) ? "∞" : String.format("%.2f", e.getValue())))
            .collect(Collectors.joining(", ", "{", "}"));
    }
}