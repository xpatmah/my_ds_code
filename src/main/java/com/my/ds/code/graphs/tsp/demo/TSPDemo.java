package com.my.ds.code.graphs.tsp.demo;

import com.my.ds.code.graphs.tsp.domain.TSPResult;
import com.my.ds.code.graphs.tsp.impl.TSPGraph;
import com.my.ds.code.graphs.tsp.impl.TSPAllTypesImpl;

/* -------------------------
   Demo
   ------------------------- */
public class TSPDemo {
    public static void main(String[] args) {
        // Example: 6-city TSP, symmetric complete graph (Euclidean-like distances provided)
        double[][] mat = {
            {0, 10, 15, 20, 11, 9},
            {10, 0, 35, 25, 12, 18},
            {15, 35, 0, 30, 20, 22},
            {20, 25, 30, 0, 21, 24},
            {11, 12, 20, 21, 0, 13},
            {9, 18, 22, 24, 13, 0}
        };

        TSPGraph<String> g = new TSPGraph<>(false, mat.length);
        // create labeled vertices A,B,C...
        for (int i = 0; i < mat.length; i++) g.addVertex(String.valueOf((char)('A' + i)));
        // fill weights
        for (int i = 0; i < mat.length; i++) for (int j = 0; j < mat.length; j++) g.addEdge(i, j, mat[i][j]);

        g.display();

        System.out.println("\n--- Brute-force TSP (exact; factorial) ---");
        TSPResult<String> bf = TSPAllTypesImpl.bruteForceTSP(g);
        System.out.println("Brute force result: " + bf);

        System.out.println("\n--- Held-Karp (DP bitmask) TSP (exact) ---");
        TSPResult<String> hk = TSPAllTypesImpl.heldKarpTSP(g);
        System.out.println("Held-Karp result: " + hk);

        System.out.println("\n--- Nearest-Neighbour heuristic ---");
        TSPResult<String> nn = TSPAllTypesImpl.nearestNeighborTSP(g);
        System.out.println("NN result (before 2-opt): " + nn);

        System.out.println("\n--- 2-Opt improvement on NN solution ---");
        TSPResult<String> nn2 = TSPAllTypesImpl.twoOptImprove(g, nn, 1000);
        System.out.println("NN + 2-Opt result: " + nn2);

        System.out.println("\n--- Notes ---");
        System.out.println("Brute-force is exact but factorial time (n!).");
        System.out.println("Held-Karp is exact with O(n^2 * 2^n) time and O(n * 2^n) memory (practical up to n ~ 20).");
        System.out.println("Nearest-Neighbour + 2-Opt gives a fast approximate solution for larger instances.");
    }
}