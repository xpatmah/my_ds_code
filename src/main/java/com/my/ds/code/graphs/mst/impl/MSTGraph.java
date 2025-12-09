package com.my.ds.code.graphs.mst.impl;

import com.my.ds.code.graphs.mst.domain.Edge;
import com.my.ds.code.graphs.mst.domain.Vertex;

import java.util.*;

/* -------------------------
   Graph (undirected, weighted)
   ------------------------- */
public class MSTGraph<T> {
    private final Map<Vertex<T>, List<Edge<T>>> adj;
    public MSTGraph() { adj = new LinkedHashMap<>(); }

    public Vertex<T> addVertex(T label) {
        Vertex<T> v = new Vertex<>(label);
        adj.putIfAbsent(v, new ArrayList<>());
        return v;
    }

    public boolean addEdge(Vertex<T> a, Vertex<T> b, double weight) {
        if (!adj.containsKey(a)) adj.put(a, new ArrayList<>());
        if (!adj.containsKey(b)) adj.put(b, new ArrayList<>());
        Edge<T> e = new Edge<>(a, b, weight);
        Edge<T> e2 = new Edge<>(b, a, weight); // undirected graph: add both directions
        adj.get(a).add(e);
        adj.get(b).add(e2);
        return true;
    }

    public Collection<Vertex<T>> vertices() { return adj.keySet(); }
    public List<Edge<T>> edges() {
        // produce unique undirected edges (each undirected edge appears twice in adj)
        List<Edge<T>> out = new ArrayList<>();
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
        return out;
    }
    private String edgeKey(Vertex<T> a, Vertex<T> b) {
        return a.getLabel() + "->" + b.getLabel();
    }

    public List<Edge<T>> neighbors(Vertex<T> v) {
        return adj.getOrDefault(v, Collections.emptyList());
    }

    public boolean contains(Vertex<T> v) { return adj.containsKey(v); }

    public int vertexIndex(Vertex<T> v) {
        // used by DisjointSet indexed version
        int i = 0;
        for (Vertex<T> x : adj.keySet()) {
            if (x.equals(v)) return i;
            i++;
        }
        return -1;
    }

    public List<Vertex<T>> vertexList() {
        return new ArrayList<>(adj.keySet());
    }

    public void display() {
        System.out.println("--- Graph ---");
        for (Vertex<T> v : adj.keySet()) {
            System.out.print(v + " -> ");
            List<String> outs = new ArrayList<>();
            for (Edge<T> e : adj.get(v)) outs.add(e.getTo() + "(" + e.getWeight() + ")");
            System.out.println(String.join(", ", outs));
        }
    }
}
