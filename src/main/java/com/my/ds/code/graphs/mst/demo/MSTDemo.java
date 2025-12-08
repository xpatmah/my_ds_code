package com.my.ds.code.graphs.mst.demo;

import com.my.ds.code.graphs.mst.domain.Edge;
import com.my.ds.code.graphs.mst.domain.Vertex;
import com.my.ds.code.graphs.mst.impl.Graph;
import com.my.ds.code.graphs.mst.impl.MST;

import java.util.List;

/* -------------------------
   Demo
   ------------------------- */
public class MSTDemo {
    public static void main(String[] args) {
        // Build sample graph (undirected, weighted)
        Graph<String> g = new Graph<>();
        Vertex<String> A = g.addVertex("A");
        Vertex<String> B = g.addVertex("B");
        Vertex<String> C = g.addVertex("C");
        Vertex<String> D = g.addVertex("D");
        Vertex<String> E = g.addVertex("E");

        g.addEdge(A, B, 4);
        g.addEdge(A, C, 1);
        g.addEdge(C, B, 2);
        g.addEdge(B, D, 5);
        g.addEdge(C, D, 8);
        g.addEdge(C, E, 10);
        g.addEdge(D, E, 2);

        g.display();

        System.out.println("\n--- Kruskal MST ---");
        MST.Pair<List<Edge<String>>, Double> kr = MST.kruskalMST(g);
        for (Edge<String> e : kr.first) System.out.println(e);
        System.out.printf("Total weight = %.2f%n", kr.second);

        System.out.println("\n--- Prim MST (start A) ---");
        MST.Pair<List<Edge<String>>, Double> pr = MST.primMST(g, A);
        for (Edge<String> e : pr.first) System.out.println(e);
        System.out.printf("Total weight = %.2f%n", pr.second);
    }
}