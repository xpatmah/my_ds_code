package com.my.ds.code.graphs.shortestPath.impl;

import com.my.ds.code.graphs.shortestPath.domain.Edge;
import com.my.ds.code.graphs.shortestPath.domain.Vertex;

import java.util.*;

/* --------------------
   Graph: directed/undirected, weighted; adjacency list
   -------------------- */
public class ShortestPathGraph<T> {
    private final boolean directed;
    private final Map<Vertex<T>, List<Edge<T>>> adj;

    public ShortestPathGraph(boolean directed) {
        this.directed = directed;
        this.adj = new LinkedHashMap<>();
    }

    public Vertex<T> addVertex(T label) {
        Vertex<T> v = new Vertex<>(label);
        adj.putIfAbsent(v, new ArrayList<>());
        return v;
    }

    public boolean addEdge(Vertex<T> a, Vertex<T> b, double weight) {
        if (!adj.containsKey(a)) adj.put(a, new ArrayList<>());
        if (!adj.containsKey(b)) adj.put(b, new ArrayList<>());
        Edge<T> e = new Edge<>(a, b, weight);
        adj.get(a).add(e);
        if (!directed) {
            // add reverse
            adj.get(b).add(new Edge<>(b, a, weight));
        }
        return true;
    }

    public Collection<Vertex<T>> vertices() { return adj.keySet(); }

    public List<Edge<T>> neighbors(Vertex<T> v) {
        return adj.getOrDefault(v, Collections.emptyList());
    }

    public List<Edge<T>> allEdgesUnique() {
        // for directed graphs: return all edges as stored
        // for undirected graphs: avoid duplicate (u->v and v->u)
        List<Edge<T>> out = new ArrayList<>();
        if (directed) {
            for (List<Edge<T>> es : adj.values()) out.addAll(es);
        } else {
            Set<String> seen = new HashSet<>();
            for (Map.Entry<Vertex<T>, List<Edge<T>>> en : adj.entrySet()) {
                for (Edge<T> e : en.getValue()) {
                    String key = edgeKey(e.getFrom(), e.getTo());
                    String rev = edgeKey(e.getTo(), e.getFrom());
                    if (!seen.contains(rev)) {
                        out.add(new Edge<>(e.getFrom(), e.getTo(), e.getWeight()));
                        seen.add(key);
                    }
                }
            }
        }
        return out;
    }

    private String edgeKey(Vertex<T> a, Vertex<T> b) {
        return String.valueOf(a.getLabel()) + "->" + String.valueOf(b.getLabel());
    }

    public boolean contains(Vertex<T> v) { return adj.containsKey(v); }

    public int vertexIndex(Vertex<T> v) { // index in iteration order
        int i = 0;
        for (Vertex<T> x : adj.keySet()) {
            if (x.equals(v)) return i;
            i++;
        }
        return -1;
    }

    public List<Vertex<T>> vertexList() { return new ArrayList<>(adj.keySet()); }

    public void display() {
        System.out.println("--- Graph (" + (directed ? "directed" : "undirected") + ") ---");
        for (Map.Entry<Vertex<T>, List<Edge<T>>> en : adj.entrySet()) {
            System.out.print(en.getKey() + " -> ");
            List<String> outs = new ArrayList<>();
            for (Edge<T> e : en.getValue()) outs.add(e.getTo() + "(" + e.getWeight() + ")");
            System.out.println(String.join(", ", outs));
        }
    }
}