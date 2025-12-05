package com.my.ds.code.graphs.simple.impl;

import com.my.ds.code.graphs.simple.contract.GraphInterface;
import com.my.ds.code.graphs.simple.domain.Edge;
import com.my.ds.code.graphs.simple.domain.Vertex;
import com.my.ds.code.graphs.simple.domain.VertexDistance;

import java.util.*;

/* --------------------
   Adjacency Matrix Graph
   -------------------- */
public class AdjacencyMatrixGraph<T> implements GraphInterface<T> {
    private final boolean directed;
    private final Map<Vertex<T>, Integer> indexMap; // vertex -> index
    private final List<Vertex<T>> vertices;         // index -> vertex
    private double[][] matrix;                      // weights, 0 means no edge for unweighted; for weighted use Double.POSITIVE_INFINITY
    private int edges;

    // constructor (initial capacity)
    public AdjacencyMatrixGraph(boolean directed, int initialCapacity, boolean weighted) {
        this.directed = directed;
        this.indexMap = new HashMap<>();
        this.vertices = new ArrayList<>();
        this.edges = 0;
        // matrix size dynamic; initialize to capacity
        int cap = Math.max(4, initialCapacity);
        matrix = new double[cap][cap];
        // choose sentinel: use Double.POSITIVE_INFINITY to mean no edge for weighted graphs,
        // and 0 for unweighted graphs — but to keep consistent we use +inf sentinel and set weight=1.0 for unweighted edges.
        for (int i = 0; i < cap; i++) Arrays.fill(matrix[i], Double.POSITIVE_INFINITY);
    }

    public AdjacencyMatrixGraph(boolean directed) {
        this(directed, 8, true);
    }

    private void ensureCapacity(int n) {
        if (n <= matrix.length) return;
        int newCap = Math.max(n, matrix.length * 2);
        double[][] newM = new double[newCap][newCap];
        for (int i = 0; i < newCap; i++) Arrays.fill(newM[i], Double.POSITIVE_INFINITY);
        for (int i = 0; i < matrix.length; i++)
            System.arraycopy(matrix[i], 0, newM[i], 0, matrix.length);
        matrix = newM;
    }

    @Override
    public Vertex<T> addVertex(T label) {
        Vertex<T> v = new Vertex<>(label);
        if (indexMap.containsKey(v)) return v; // already present (by label)
        int idx = vertices.size();
        vertices.add(v);
        indexMap.put(v, idx);
        ensureCapacity(vertices.size());
        return v;
    }

    @Override
    public boolean removeVertex(Vertex<T> v) {
        Integer idxObj = indexMap.get(v);
        if (idxObj == null) return false;
        int idx = idxObj;
        // remove from vertices list and matrix: we'll compact by moving last vertex into idx
        int last = vertices.size() - 1;
        Vertex<T> lastV = vertices.get(last);

        // move last vertex to idx (if not same)
        if (idx != last) {
            vertices.set(idx, lastV);
            indexMap.put(lastV, idx);
            // move matrix rows/cols
            for (int i = 0; i < vertices.size(); i++) {
                matrix[idx][i] = matrix[last][i];
                matrix[i][idx] = matrix[i][last];
            }
        }
        // clear last row/col
        for (int i = 0; i < vertices.size(); i++) {
            matrix[last][i] = Double.POSITIVE_INFINITY;
            matrix[i][last] = Double.POSITIVE_INFINITY;
        }
        vertices.remove(last);
        indexMap.remove(v);

        // recalc edge count (cheap approach)
        recomputeEdgeCount();
        return true;
    }

    @Override
    public Edge<T> addEdge(Vertex<T> from, Vertex<T> to) { return addEdge(from, to, 1.0); }

    @Override
    public Edge<T> addEdge(Vertex<T> from, Vertex<T> to, double weight) {
        Integer fi = indexMap.get(from), ti = indexMap.get(to);
        if (fi == null || ti == null) throw new IllegalArgumentException("Vertices must belong to this graph");
        int i = fi, j = ti;
        if (Double.isInfinite(matrix[i][j])) edges++;
        matrix[i][j] = weight;
        if (!directed) matrix[j][i] = weight;
        return new Edge<>(from, to, weight);
    }

    @Override
    public boolean removeEdge(Vertex<T> from, Vertex<T> to) {
        Integer fi = indexMap.get(from), ti = indexMap.get(to);
        if (fi == null || ti == null) return false;
        int i = fi, j = ti;
        if (Double.isInfinite(matrix[i][j])) return false;
        matrix[i][j] = Double.POSITIVE_INFINITY;
        if (!directed) matrix[j][i] = Double.POSITIVE_INFINITY;
        edges--;
        return true;
    }

    @Override
    public boolean hasEdge(Vertex<T> from, Vertex<T> to) {
        Integer fi = indexMap.get(from), ti = indexMap.get(to);
        if (fi == null || ti == null) return false;
        return !Double.isInfinite(matrix[fi][ti]);
    }

    @Override
    public List<Vertex<T>> neighbors(Vertex<T> v) {
        List<Vertex<T>> out = new ArrayList<>();
        Integer idx = indexMap.get(v);
        if (idx == null) return out;
        for (int j = 0; j < vertices.size(); j++) {
            if (!Double.isInfinite(matrix[idx][j])) out.add(vertices.get(j));
        }
        return out;
    }

    // ---------------------
    // Traversals / algorithms
    // ---------------------
    @Override
    public List<Vertex<T>> bfs(Vertex<T> start) {
        List<Vertex<T>> order = new ArrayList<>();
        Integer sIdx = indexMap.get(start);
        if (sIdx == null) return order;
        boolean[] seen = new boolean[vertices.size()];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(sIdx); seen[sIdx] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(vertices.get(u));
            for (int v = 0; v < vertices.size(); v++) {
                if (!Double.isInfinite(matrix[u][v]) && !seen[v]) {
                    seen[v] = true; q.add(v);
                }
            }
        }
        return order;
    }

    @Override
    public List<Vertex<T>> dfsRecursive(Vertex<T> start) {
        List<Vertex<T>> order = new ArrayList<>();
        Integer sIdx = indexMap.get(start);
        if (sIdx == null) return order;
        boolean[] visited = new boolean[vertices.size()];
        dfsRecHelper(sIdx, visited, order);
        return order;
    }

    private void dfsRecHelper(int u, boolean[] visited, List<Vertex<T>> order) {
        visited[u] = true;
        order.add(vertices.get(u));
        for (int v = 0; v < vertices.size(); v++) {
            if (!Double.isInfinite(matrix[u][v]) && !visited[v]) dfsRecHelper(v, visited, order);
        }
    }

    @Override
    public List<Vertex<T>> dfsIterative(Vertex<T> start) {
        List<Vertex<T>> order = new ArrayList<>();
        Integer sIdx = indexMap.get(start);
        if (sIdx == null) return order;
        boolean[] visited = new boolean[vertices.size()];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(sIdx);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (visited[u]) continue;
            visited[u] = true;
            order.add(vertices.get(u));
            // push neighbors in reverse order for natural ordering
            for (int v = vertices.size() - 1; v >= 0; v--) {
                if (!Double.isInfinite(matrix[u][v]) && !visited[v]) stack.push(v);
            }
        }
        return order;
    }

    @Override
    public Map<Vertex<T>, Double> dijkstra(Vertex<T> source) {
        Map<Vertex<T>, Double> dist = new HashMap<>();
        Vertex<T> src = source;
        Integer sIdx = indexMap.get(src);
        if (sIdx == null) return dist;
        int n = vertices.size();
        for (Vertex<T> v : vertices) dist.put(v, Double.POSITIVE_INFINITY);
        dist.put(src, 0.0);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));
        // pq holds {vertexIndex, distance} but distance as double casted to long? We'll keep double separate via map; store index and distance as double in wrapper
        PriorityQueue<VertexDistance> heap = new PriorityQueue<>(Comparator.comparingDouble(VertexDistance::getDistance));
        heap.add(new VertexDistance(sIdx, 0.0));

        boolean[] visited = new boolean[n];
        while (!heap.isEmpty()) {
            VertexDistance vd = heap.poll();
            int u = vd.getIndex();
            if (visited[u]) continue;
            visited[u] = true;
            Vertex<T> uv = vertices.get(u);
            for (int v = 0; v < n; v++) {
                if (!Double.isInfinite(matrix[u][v])) {
                    double w = matrix[u][v];
                    double nd = dist.get(uv) + w;
                    Vertex<T> vv = vertices.get(v);
                    if (nd < dist.get(vv)) {
                        dist.put(vv, nd);
                        heap.add(new VertexDistance(v, nd));
                    }
                }
            }
        }
        return dist;
    }



    @Override
    public List<Vertex<T>> topologicalSort() {
        if (!directed) throw new UnsupportedOperationException("Topological sort only for directed graphs");
        int n = vertices.size();
        int[] indeg = new int[n];
        for (int u = 0; u < n; u++) for (int v = 0; v < n; v++) if (!Double.isInfinite(matrix[u][v])) indeg[v]++;
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (indeg[i] == 0) q.add(i);
        List<Vertex<T>> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(vertices.get(u));
            for (int v = 0; v < n; v++) {
                if (!Double.isInfinite(matrix[u][v])) {
                    indeg[v]--;
                    if (indeg[v] == 0) q.add(v);
                }
            }
        }
        if (order.size() != n) return Collections.emptyList(); // cycle present
        return order;
    }

    @Override
    public boolean detectCycle() {
        if (!directed) {
            // simple cycle detection for undirected graphs: use union-find or DFS with parent check
            boolean[] visited = new boolean[vertices.size()];
            for (int i = 0; i < vertices.size(); i++) {
                if (!visited[i]) if (dfsCycleUndirected(i, visited, -1)) return true;
            }
            return false;
        } else {
            // directed: use colors
            int n = vertices.size();
            int[] color = new int[n]; // 0=white,1=gray,2=black
            for (int i = 0; i < n; i++) if (color[i] == 0) if (dfsCycleDirected(i, color)) return true;
            return false;
        }
    }

    private boolean dfsCycleUndirected(int u, boolean[] visited, int parent) {
        visited[u] = true;
        for (int v = 0; v < vertices.size(); v++) {
            if (Double.isInfinite(matrix[u][v])) continue;
            if (!visited[v]) {
                if (dfsCycleUndirected(v, visited, u)) return true;
            } else if (v != parent) return true;
        }
        return false;
    }

    private boolean dfsCycleDirected(int u, int[] color) {
        color[u] = 1;
        for (int v = 0; v < vertices.size(); v++) {
            if (Double.isInfinite(matrix[u][v])) continue;
            if (color[v] == 1) return true;
            if (color[v] == 0 && dfsCycleDirected(v, color)) return true;
        }
        color[u] = 2;
        return false;
    }

    private void recomputeEdgeCount() {
        int c = 0;
        int n = vertices.size();
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) if (!Double.isInfinite(matrix[i][j])) c++;
        if (!directed) c /= 2;
        edges = c;
    }

    @Override
    public void display() {
        System.out.println("--- Adjacency Matrix Graph (" + (directed ? "directed" : "undirected") + ") ---");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i) + ": ");
            List<String> outs = new ArrayList<>();
            for (int j = 0; j < vertices.size(); j++) {
                if (!Double.isInfinite(matrix[i][j])) outs.add(vertices.get(j) + "(" + matrix[i][j] + ")");
            }
            System.out.println(String.join(", ", outs));
        }
        System.out.println("Vertices: " + vertices.size() + ", Edges: " + edges);
    }

    @Override
    public int size() { return vertices.size(); }

    @Override
    public int edgeCount() { return edges; }
}