package com.my.ds.code.graphs.articulation_and_bridges;

import java.util.*;

/**
 * Articulation Points and Bridges using Tarjan's Algorithm
 */
public class ArticulationPointBridge {

    private int time = 0;

    // ============================
    // Find Articulation Points
    // ============================
    public Set<Integer> findArticulationPoints(int n, List<List<Integer>> graph) {
        boolean[] visited = new boolean[n];
        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        Set<Integer> articulationPoints = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsAP(i, visited, disc, low, parent, articulationPoints, graph);
            }
        }
        return articulationPoints;
    }

    private void dfsAP(int u, boolean[] visited, int[] disc, int[] low,
                       int[] parent, Set<Integer> ap, List<List<Integer>> graph) {

        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : graph.get(u)) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                dfsAP(v, visited, disc, low, parent, ap, graph);

                low[u] = Math.min(low[u], low[v]);

                // Case 1: Root with 2+ children
                if (parent[u] == -1 && children > 1)
                    ap.add(u);

                // Case 2: Non-root
                if (parent[u] != -1 && low[v] >= disc[u])
                    ap.add(u);

            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // ============================
    // Find Bridges
    // ============================
    public List<int[]> findBridges(int n, List<List<Integer>> graph) {
        boolean[] visited = new boolean[n];
        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        List<int[]> bridges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsBridge(i, visited, disc, low, parent, bridges, graph);
            }
        }
        return bridges;
    }

    private void dfsBridge(int u, boolean[] visited, int[] disc, int[] low,
                           int[] parent, List<int[]> bridges, List<List<Integer>> graph) {

        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : graph.get(u)) {
            if (!visited[v]) {
                parent[v] = u;
                dfsBridge(v, visited, disc, low, parent, bridges, graph);

                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    bridges.add(new int[]{u, v});
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // ============================
    // Utility: Build Graph
    // ============================
    private static List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        return graph;
    }

    // ============================
    // MAIN METHOD
    // ============================
    public static void main(String[] args) {

        int n = 7;
        int[][] edges = {
            {0,1}, {1,2}, {2,0},
            {1,3},
            {3,4},
            {4,5}, {5,3},
            {5,6}
        };

        List<List<Integer>> graph = buildGraph(n, edges);
        ArticulationPointBridge solver = new ArticulationPointBridge();

        System.out.println("Articulation Points:");
        System.out.println(solver.findArticulationPoints(n, graph));

        System.out.println("\nBridges:");
        List<int[]> bridges = solver.findBridges(n, graph);
        for (int[] b : bridges) {
            System.out.println(b[0] + " - " + b[1]);
        }
    }
}