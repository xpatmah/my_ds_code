package com.my.ds.code.graphs.shortestPath.demo;

import com.my.ds.code.graphs.shortestPath.domain.FWResult;
import com.my.ds.code.graphs.shortestPath.domain.SPResult;
import com.my.ds.code.graphs.shortestPath.domain.Vertex;
import com.my.ds.code.graphs.shortestPath.impl.ShortestPathGraph;
import com.my.ds.code.graphs.shortestPath.impl.ShortestPathAlgorithms;

import java.util.List;
import java.util.Map;

/* --------------------
   Demo
   -------------------- */
public class ShortestPathAlgorithmsDemo {
    public static void main(String[] args) {
        // Build a sample directed weighted graph (includes one negative edge but no negative cycle)
        ShortestPathGraph<String> g = new ShortestPathGraph<>(true);

        Vertex<String> s = g.addVertex("S");
        Vertex<String> a = g.addVertex("A");
        Vertex<String> b = g.addVertex("B");
        Vertex<String> c = g.addVertex("C");
        Vertex<String> d = g.addVertex("D");
        Vertex<String> e = g.addVertex("E");

        g.addEdge(s, a, 6);
        g.addEdge(s, b, 7);
        g.addEdge(a, c, 5);
        g.addEdge(a, b, 8);
        g.addEdge(b, c, -3); // negative edge but no negative cycle
        g.addEdge(b, d, 9);
        g.addEdge(c, d, 7);
        g.addEdge(d, e, 2);
        g.addEdge(c, e, -2);

        g.display();

        System.out.println("\n--- Dijkstra from S (non-negative edges only) ---");
        // Note: Dijkstra will throw IllegalArgumentException due to negative edges in this demo graph.
        // For demonstration, run Dijkstra on a graph without negative edges:
        ShortestPathGraph<String> gPos = new ShortestPathGraph<>(true);
        Vertex<String> s2 = gPos.addVertex("S"); Vertex<String> a2 = gPos.addVertex("A"); Vertex<String> b2 = gPos.addVertex("B");
        Vertex<String> c2 = gPos.addVertex("C"); Vertex<String> d2 = gPos.addVertex("D");
        gPos.addEdge(s2, a2, 6); gPos.addEdge(s2, b2, 7); gPos.addEdge(a2, c2, 5); gPos.addEdge(a2, b2, 8);
        gPos.addEdge(b2, c2, 3); gPos.addEdge(b2, d2, 9); gPos.addEdge(c2, d2, 7);

        SPResult<String> dres = ShortestPathAlgorithms.dijkstra(gPos, s2);
        printSingleSourceResult(dres, s2);

        System.out.println("\n--- Bellman-Ford from S (supports negative edges) ---");
        SPResult<String> bf = ShortestPathAlgorithms.bellmanFord(g, s);
        if (bf.hasNegativeCycle()) {
            System.out.println("Graph has a negative-weight cycle reachable from source.");
        } else {
            printSingleSourceResult(bf, s);
        }

        System.out.println("\n--- Floyd-Warshall (all pairs) ---");
        FWResult<String> fw = ShortestPathAlgorithms.floydWarshall(g);
        printFWResult(fw);
    }

    private static <T> void printSingleSourceResult(SPResult<T> res, Vertex<T> source) {
        System.out.println("Distances from " + source + ":");
        for (Map.Entry<Vertex<T>, Double> e : res.getDist().entrySet()) {
            String d = Double.isInfinite(e.getValue()) ? "∞" : String.format("%.2f", e.getValue());
            System.out.println("  " + e.getKey() + " : " + d);
        }

        // print paths
        System.out.println("Paths:");
        for (Vertex<T> v : res.getDist().keySet()) {
            if (v.equals(source)) continue;
            List<Vertex<T>> path = ShortestPathAlgorithms.reconstructPath(res.getPred(), source, v);
            if (path.isEmpty()) System.out.println("  " + source + " -> " + v + " : no path");
            else System.out.println("  " + source + " -> " + v + " : " + path);
        }
    }

    private static <T> void printFWResult(FWResult<T> fw) {
        List<Vertex<T>> verts = fw.vertices;
        int n = verts.size();
        System.out.println("All-pairs shortest distances matrix:");
        System.out.print("    ");
        for (Vertex<T> v : verts) System.out.printf("%8s", v);
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("%4s", verts.get(i));
            for (int j = 0; j < n; j++) {
                double d = fw.dist[i][j];
                String out;
                if (d == Double.POSITIVE_INFINITY) out = "   ∞   ";
                else if (d == Double.NEGATIVE_INFINITY) out = "  -∞   ";
                else out = String.format("%7.2f", d);
                System.out.print(out);
            }
            System.out.println();
        }

        // show a couple of paths
        System.out.println("\nExample path reconstructions:");
        if (n >= 2) {
            List<Vertex<T>> p = fw.reconstructPath(0, Math.min(3, n - 1));
            System.out.println("Path " + verts.get(0) + " -> " + verts.get(Math.min(3, n - 1)) + " : " + p);
        }
    }
}