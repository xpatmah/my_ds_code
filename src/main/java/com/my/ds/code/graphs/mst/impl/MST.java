package com.my.ds.code.graphs.mst.impl;

import com.my.ds.code.graphs.mst.domain.Edge;
import com.my.ds.code.graphs.mst.domain.Vertex;

import java.util.*;

/* -------------------------
   MST algorithms
   ------------------------- */
public class MST {

    // Kruskal: returns list of edges in MST and total weight
    public static <T> Pair<List<Edge<T>>, Double> kruskalMST(Graph<T> g) {
        List<Edge<T>> edges = g.edges();
        // sort by weight
        edges.sort(Comparator.comparingDouble(Edge::getWeight));

        List<Vertex<T>> vlist = g.vertexList();
        int n = vlist.size();
        Map<Vertex<T>, Integer> idx = new HashMap<>();
        for (int i = 0; i < n; i++) idx.put(vlist.get(i), i);

        DisjointSet ds = new DisjointSet(n);
        List<Edge<T>> mst = new ArrayList<>();
        double total = 0.0;

        for (Edge<T> e : edges) {
            int u = idx.get(e.getFrom());
            int v = idx.get(e.getTo());
            if (ds.find(u) != ds.find(v)) {
                ds.union(u, v);
                mst.add(e);
                total += e.getWeight();
            }
            if (mst.size() == n - 1) break;
        }
        return new Pair<>(mst, total);
    }

    // Prim: returns list of edges in MST and total weight. start must be a vertex in graph
    public static <T> Pair<List<Edge<T>>, Double> primMST(Graph<T> g, Vertex<T> start) {
        if (!g.contains(start)) throw new IllegalArgumentException("Start vertex not in graph");

        Set<Vertex<T>> inMST = new HashSet<>();
        List<Edge<T>> mst = new ArrayList<>();
        double total = 0.0;

        // min-heap of edges by weight (edge leads from tree to outside)
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));

        inMST.add(start);
        for (Edge<T> e : g.neighbors(start)) pq.add(e);

        while (!pq.isEmpty() && inMST.size() < g.vertexList().size()) {
            Edge<T> e = pq.poll();
            Vertex<T> u = e.getFrom();
            Vertex<T> v = e.getTo();
            // the edge may be from or to a vertex already in tree; ensure we pick crossing edge
            if (inMST.contains(v) && inMST.contains(u)) continue;

            Vertex<T> next = inMST.contains(u) ? v : u;
            if (inMST.contains(next)) continue;

            // add edge
            mst.add(e);
            total += e.getWeight();
            inMST.add(next);

            // push all edges from 'next' to vertices not yet in MST
            for (Edge<T> ne : g.neighbors(next)) {
                if (!inMST.contains(ne.getTo())) pq.add(ne);
            }
        }

        return new Pair<>(mst, total);
    }

    // helper container
    public static class Pair<A,B> {
        public final A first; public final B second;
        public Pair(A f, B s) { first = f; second = s; }
    }
}
