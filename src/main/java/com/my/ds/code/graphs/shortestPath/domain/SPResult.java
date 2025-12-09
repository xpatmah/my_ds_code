package com.my.ds.code.graphs.shortestPath.domain;

import java.util.Map;

/* --------------------
   Shortest path results containers
   -------------------- */
public class SPResult<T> {
    // distances and predecessors for path reconstruction
    private final Map<Vertex<T>, Double> dist;
    private final Map<Vertex<T>, Vertex<T>> pred;
    private final boolean negativeCycle; // for Bellman-Ford

    public SPResult(Map<Vertex<T>, Double> dist, Map<Vertex<T>, Vertex<T>> pred) {
        this(dist, pred, false);
    }
    public SPResult(Map<Vertex<T>, Double> dist, Map<Vertex<T>, Vertex<T>> pred, boolean negativeCycle) {
        this.dist = dist; this.pred = pred; this.negativeCycle = negativeCycle;
    }

    public Map<Vertex<T>, Double> getDist() { return dist; }
    public Map<Vertex<T>, Vertex<T>> getPred() { return pred; }
    public boolean hasNegativeCycle() { return negativeCycle; }
}