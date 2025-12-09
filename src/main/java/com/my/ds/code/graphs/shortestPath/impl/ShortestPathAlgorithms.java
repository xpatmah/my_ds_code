package com.my.ds.code.graphs.shortestPath.impl;

import com.my.ds.code.graphs.shortestPath.domain.*;

import java.util.*;

/* --------------------
   Shortest Path algorithms
   -------------------- */
public class ShortestPathAlgorithms {

    // ---------------------
    // Dijkstra: single-source shortest path for non-negative weights.
    // Returns distances map and predecessor map (to reconstruct paths).
    // ---------------------
    public static <T> SPResult<T> dijkstra(ShortestPathGraph<T> g, Vertex<T> source) {
        if (!g.contains(source)) throw new IllegalArgumentException("Source not in graph");

        // dist map init
        Map<Vertex<T>, Double> dist = new HashMap<>();
        Map<Vertex<T>, Vertex<T>> pred = new HashMap<>();
        for (Vertex<T> v : g.vertices()) {
            dist.put(v, Double.POSITIVE_INFINITY);
            pred.put(v, null);
        }
        dist.put(source, 0.0);

        // min-priority queue
        PriorityQueue<Pair<Vertex<T>>> pq = new PriorityQueue<>(Comparator.comparingDouble(Pair::getDist));
        pq.add(new Pair<>(source, 0.0));

        Set<Vertex<T>> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            Pair<Vertex<T>> cur = pq.poll();
            Vertex<T> u = cur.getVertex();
            double d = cur.getDist();
            if (visited.contains(u)) continue;
            visited.add(u);

            for (Edge<T> e : g.neighbors(u)) {
                Vertex<T> v = e.getTo();
                double w = e.getWeight();
                if (w < 0) throw new IllegalArgumentException("Graph contains negative weight edge; Dijkstra not applicable.");
                double nd = d + w;
                if (nd < dist.get(v)) {
                    dist.put(v, nd);
                    pred.put(v, u);
                    pq.add(new Pair<>(v, nd));
                }
            }
        }

        return new SPResult<>(dist, pred);
    }

    // ---------------------
    // Bellman-Ford: single-source shortest path; supports negative weights.
    // Returns distances and preds. If a negative cycle reachable from source exists,
    // sets negativeCycle=true in returned SPResult.
    // ---------------------
    public static <T> SPResult<T> bellmanFord(ShortestPathGraph<T> g, Vertex<T> source) {
        if (!g.contains(source)) throw new IllegalArgumentException("Source not in graph");
        List<Vertex<T>> vlist = g.vertexList();
        int n = vlist.size();
        Map<Vertex<T>, Integer> idx = new HashMap<>();
        for (int i = 0; i < n; i++) idx.put(vlist.get(i), i);

        double[] distArr = new double[n];
        Vertex<T>[] predArr = (Vertex<T>[]) new Vertex[n];

        Arrays.fill(distArr, Double.POSITIVE_INFINITY);
        distArr[idx.get(source)] = 0.0;

        List<Edge<T>> edges = g.allEdgesUnique(); // edges as list (for directed: all edges; for undirected: unique edges)

        // relax edges n-1 times
        for (int iter = 0; iter < n - 1; iter++) {
            boolean changed = false;
            for (Edge<T> e : edges) {
                int u = idx.get(e.getFrom()), v = idx.get(e.getTo());
                double w = e.getWeight();
                if (distArr[u] != Double.POSITIVE_INFINITY && distArr[u] + w < distArr[v]) {
                    distArr[v] = distArr[u] + w;
                    predArr[v] = e.getFrom();
                    changed = true;
                }
            }
            if (!changed) break;
        }

        // check for negative-weight cycles reachable from source
        boolean negativeCycle = false;
        for (Edge<T> e : edges) {
            int u = idx.get(e.getFrom()), v = idx.get(e.getTo());
            double w = e.getWeight();
            if (distArr[u] != Double.POSITIVE_INFINITY && distArr[u] + w < distArr[v]) {
                negativeCycle = true;
                break;
            }
        }

        Map<Vertex<T>, Double> dist = new HashMap<>();
        Map<Vertex<T>, Vertex<T>> pred = new HashMap<>();
        for (int i = 0; i < n; i++) {
            dist.put(vlist.get(i), distArr[i]);
            pred.put(vlist.get(i), predArr[i]);
        }
        return new SPResult<>(dist, pred, negativeCycle);
    }

    public static <T> FWResult<T> floydWarshall(ShortestPathGraph<T> g) {
        List<Vertex<T>> vlist = g.vertexList();
        int n = vlist.size();
        Map<Vertex<T>, Integer> idx = new HashMap<>();
        for (int i = 0; i < n; i++) idx.put(vlist.get(i), i);

        final double INF = Double.POSITIVE_INFINITY;
        double[][] dist = new double[n][n];
        Integer[][] next = new Integer[n][n];

        // init
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            Arrays.fill(next[i], null);
            dist[i][i] = 0.0;
            next[i][i] = i;
        }

        for (Vertex<T> u : vlist) {
            int ui = idx.get(u);
            for (Edge<T> e : g.neighbors(u)) {
                int vi = idx.get(e.getTo());
                dist[ui][vi] = Math.min(dist[ui][vi], e.getWeight()); // in case of multiple edges
                next[ui][vi] = vi;
            }
        }

        // main triple loop
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 0; j < n; j++) {
                    if (dist[k][j] == INF) continue;
                    double nd = dist[i][k] + dist[k][j];
                    if (nd < dist[i][j]) {
                        dist[i][j] = nd;
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // detect negative cycles: if dist[i][i] < 0 then node i is in/affected by negative cycle
        for (int k = 0; k < n; k++) {
            if (dist[k][k] < 0) {
                // propagate -INF to affected pairs
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (dist[i][k] != INF && dist[k][j] != INF) {
                            dist[i][j] = Double.NEGATIVE_INFINITY;
                            next[i][j] = null;
                        }
                    }
                }
            }
        }

        return new FWResult<>(dist, next, vlist);
    }

    // ---------------------
    // Utility: reconstruct path from pred map (single-source)
    // ---------------------
    public static <T> List<Vertex<T>> reconstructPath(Map<Vertex<T>, Vertex<T>> pred, Vertex<T> source, Vertex<T> target) {
        List<Vertex<T>> path = new LinkedList<>();
        if (!pred.containsKey(target)) return path;
        Vertex<T> cur = target;
        // if unreachable, pred might be null and dist INF (caller should check)
        while (cur != null) {
            path.add(0, cur);
            if (cur.equals(source)) break;
            cur = pred.get(cur);
        }
        if (path.isEmpty() || !path.get(0).equals(source)) return Collections.emptyList(); // no path
        return path;
    }
}