package com.my.ds.code.graphs.simple.impl;

import com.my.ds.code.graphs.simple.contract.GraphInterface;
import com.my.ds.code.graphs.simple.domain.Edge;
import com.my.ds.code.graphs.simple.domain.Pair;
import com.my.ds.code.graphs.simple.domain.Vertex;

import java.util.*;
import java.util.stream.Collectors;

/* --------------------
   Adjacency List Graph
   -------------------- */
public class AdjacencyListGraph<T> implements GraphInterface<T> {
    private final boolean directed;
    private final Map<Vertex<T>, List<Edge<T>>> adj;
    private int edges;

    public AdjacencyListGraph(boolean directed) {
        this.directed = directed;
        this.adj = new LinkedHashMap<>();
        this.edges = 0;
    }

    @Override
    public Vertex<T> addVertex(T label) {
        Vertex<T> v = new Vertex<>(label);
        if (!adj.containsKey(v)) adj.put(v, new ArrayList<>());
        return v;
    }

    @Override
    public boolean removeVertex(Vertex<T> v) {
        if (!adj.containsKey(v)) return false;
        // remove all edges to/from v
        adj.remove(v);
        for (List<Edge<T>> edgesList : adj.values()) {
            Iterator<Edge<T>> it = edgesList.iterator();
            while (it.hasNext()) {
                Edge<T> e = it.next();
                if (e.getTo().equals(v)) {
                    it.remove();
                    edges--;
                }
            }
        }
        return true;
    }

    @Override
    public Edge<T> addEdge(Vertex<T> from, Vertex<T> to) { return addEdge(from, to, 1.0); }

    @Override
    public Edge<T> addEdge(Vertex<T> from, Vertex<T> to, double weight) {
        if (!adj.containsKey(from)) adj.put(from, new ArrayList<>());
        if (!adj.containsKey(to)) adj.put(to, new ArrayList<>());
        // check existing
        List<Edge<T>> list = adj.get(from);
        for (Edge<T> e : list) if (e.getTo().equals(to)) { e.setWeight(weight); return e; }
        Edge<T> e = new Edge<>(from, to, weight);
        list.add(e);
        edges++;
        if (!directed) {
            // add reverse
            adj.get(to).add(new Edge<>(to, from, weight));
        }
        return e;
    }

    @Override
    public boolean removeEdge(Vertex<T> from, Vertex<T> to) {
        List<Edge<T>> list = adj.get(from);
        if (list == null) return false;
        boolean removed = list.removeIf(e -> e.getTo().equals(to));
        if (removed) {
            edges--;
            if (!directed) {
                List<Edge<T>> list2 = adj.get(to);
                if (list2 != null) list2.removeIf(e -> e.getTo().equals(from));
            }
        }
        return removed;
    }

    @Override
    public boolean hasEdge(Vertex<T> from, Vertex<T> to) {
        List<Edge<T>> list = adj.get(from);
        if (list == null) return false;
        return list.stream().anyMatch(e -> e.getTo().equals(to));
    }

    @Override
    public List<Vertex<T>> neighbors(Vertex<T> v) {
        List<Vertex<T>> out = new ArrayList<>();
        List<Edge<T>> list = adj.get(v);
        if (list == null) return out;
        for (Edge<T> e : list) out.add(e.getTo());
        return out;
    }

    // ---------------------
    // Traversals / algorithms
    // ---------------------
    @Override
    public List<Vertex<T>> bfs(Vertex<T> start) {
        List<Vertex<T>> order = new ArrayList<>();
        if (!adj.containsKey(start)) return order;
        Set<Vertex<T>> seen = new HashSet<>();
        Queue<Vertex<T>> q = new ArrayDeque<>();
        q.add(start); seen.add(start);
        while (!q.isEmpty()) {
            Vertex<T> u = q.poll();
            order.add(u);
            for (Edge<T> e : adj.getOrDefault(u, Collections.emptyList())) {
                if (!seen.contains(e.getTo())) { seen.add(e.getTo()); q.add(e.getTo()); }
            }
        }
        return order;
    }

    @Override
    public List<Vertex<T>> dfsRecursive(Vertex<T> start) {
        List<Vertex<T>> order = new ArrayList<>();
        if (!adj.containsKey(start)) return order;
        Set<Vertex<T>> seen = new HashSet<>();
        dfsRec(start, seen, order);
        return order;
    }

    private void dfsRec(Vertex<T> u, Set<Vertex<T>> seen, List<Vertex<T>> order) {
        seen.add(u); order.add(u);
        for (Edge<T> e : adj.getOrDefault(u, Collections.emptyList())) {
            if (!seen.contains(e.getTo())) dfsRec(e.getTo(), seen, order);
        }
    }

    @Override
    public List<Vertex<T>> dfsIterative(Vertex<T> start) {
        List<Vertex<T>> order = new ArrayList<>();
        if (!adj.containsKey(start)) return order;
        Set<Vertex<T>> seen = new HashSet<>();
        Deque<Vertex<T>> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            Vertex<T> u = stack.pop();
            if (seen.contains(u)) continue;
            seen.add(u);
            order.add(u);
            List<Edge<T>> nbrs = adj.getOrDefault(u, Collections.emptyList());
            // push neighbors in reverse to preserve ordering
            ListIterator<Edge<T>> it = nbrs.listIterator(nbrs.size());
            while (it.hasPrevious()) {
                Edge<T> e = it.previous();
                if (!seen.contains(e.getTo())) stack.push(e.getTo());
            }
        }
        return order;
    }

    @Override
    public Map<Vertex<T>, Double> dijkstra(Vertex<T> source) {
        Map<Vertex<T>, Double> dist = new HashMap<>();
        if (!adj.containsKey(source)) return dist;
        for (Vertex<T> v : adj.keySet()) dist.put(v, Double.POSITIVE_INFINITY);
        dist.put(source, 0.0);

        PriorityQueue<Pair<T>> pq = new PriorityQueue<>(Comparator.comparingDouble(Pair::getDist));
        pq.add(new Pair<>(source, 0.0));
        Set<Vertex<T>> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            Pair<T> p = pq.poll();
            Vertex<T> u = p.getVertex();
            if (visited.contains(u)) continue;
            visited.add(u);
            for (Edge<T> e : adj.getOrDefault(u, Collections.emptyList())) {
                Vertex<T> v = e.getTo();
                double nd = dist.get(u) + e.getWeight();
                if (nd < dist.get(v)) {
                    dist.put(v, nd);
                    pq.add(new Pair<>(v, nd));
                }
            }
        }
        return dist;
    }

    // Topological sort only works for directed acyclic a graph which has direction arrow and does not contain cycle.
    @Override
    public List<Vertex<T>> topologicalSort() {
        if (!directed) throw new UnsupportedOperationException("Topological sort only for directed graphs");
        // Kahn's algorithm
        Map<Vertex<T>, Integer> indeg = new HashMap<>();
        for (Vertex<T> v : adj.keySet()) indeg.put(v, 0);
        for (Vertex<T> u : adj.keySet()) for (Edge<T> e : adj.get(u)) indeg.put(e.getTo(), indeg.getOrDefault(e.getTo(),0)+1);
        Queue<Vertex<T>> q = new ArrayDeque<>();
        for (Map.Entry<Vertex<T>, Integer> en : indeg.entrySet()) if (en.getValue() == 0) q.add(en.getKey());
        List<Vertex<T>> order = new ArrayList<>();
        while (!q.isEmpty()) {
            Vertex<T> u = q.poll(); order.add(u);
            for (Edge<T> e : adj.getOrDefault(u, Collections.emptyList())) {
                Vertex<T> v = e.getTo();
                indeg.put(v, indeg.get(v)-1);
                if (indeg.get(v) == 0) q.add(v);
            }
        }
        if (order.size() != adj.size()) return Collections.emptyList(); // cycle
        return order;
    }

    @Override
    public boolean detectCycle() {
        if (!directed) {
            // undirected cycle detection via DFS with parent
            Set<Vertex<T>> seen = new HashSet<>();
            for (Vertex<T> v : adj.keySet()) if (!seen.contains(v)) if (dfsCycleUndirected(v, null, seen)) return true;
            return false;
        } else {
            // directed cycle: DFS colors
            Map<Vertex<T>, Integer> color = new HashMap<>(); // 0 white,1 gray,2 black
            for (Vertex<T> v : adj.keySet()) color.put(v, 0);
            for (Vertex<T> v : adj.keySet()) if (color.get(v) == 0) if (dfsCycleDirected(v, color)) return true;
            return false;
        }
    }

    private boolean dfsCycleUndirected(Vertex<T> u, Vertex<T> parent, Set<Vertex<T>> seen) {
        seen.add(u);
        for (Edge<T> e : adj.getOrDefault(u, Collections.emptyList())) {
            Vertex<T> v = e.getTo();
            if (!seen.contains(v)) {
                if (dfsCycleUndirected(v, u, seen)) return true;
            } else if (!v.equals(parent)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfsCycleDirected(Vertex<T> u, Map<Vertex<T>, Integer> color) {
        color.put(u, 1);
        for (Edge<T> e : adj.getOrDefault(u, Collections.emptyList())) {
            Vertex<T> v = e.getTo();
            if (color.get(v) == 1) return true;
            if (color.get(v) == 0 && dfsCycleDirected(v, color)) return true;
        }
        color.put(u, 2);
        return false;
    }

    @Override
    public void display() {
        System.out.println("--- Adjacency List Graph (" + (directed ? "directed" : "undirected") + ") ---");
        for (Map.Entry<Vertex<T>, List<Edge<T>>> en : adj.entrySet()) {
            String outs = en.getValue().stream().map(Edge::toString).collect(Collectors.joining(", "));
            System.out.println(en.getKey() + ": " + outs);
        }
        System.out.println("Vertices: " + adj.size() + ", Edges: " + edges);
    }

    @Override
    public int size() { return adj.size(); }

    @Override
    public int edgeCount() { return edges; }
}
