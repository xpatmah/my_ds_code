package com.my.ds.code.graphs.tsp.impl;

import com.my.ds.code.graphs.tsp.domain.Vertex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* -------------------------
   Graph: uses adjacency matrix
   ------------------------- */
public class TSPGraph<T> {
    private final boolean directed;
    private final List<Vertex<T>> vertices;
    private double[][] weight; // weight[i][j] = weight from i -> j; Double.POSITIVE_INFINITY means no direct edge

    public TSPGraph(boolean directed, int initialCapacity) {
        this.directed = directed;
        this.vertices = new ArrayList<>();
        int cap = Math.max(4, initialCapacity);
        this.weight = new double[cap][cap];
        for (int i = 0; i < cap; i++) Arrays.fill(this.weight[i], Double.POSITIVE_INFINITY);
    }

    public TSPGraph(boolean directed) { this(directed, 8); }

    public Vertex<T> addVertex(T label) {
        Vertex<T> v = new Vertex<>(label);
        int idx = vertices.size();
        vertices.add(v);
        ensureCapacity(vertices.size());
        return v;
    }

    private void ensureCapacity(int n) {
        if (n <= weight.length) return;
        int newCap = Math.max(n, weight.length * 2);
        double[][] newW = new double[newCap][newCap];
        for (int i = 0; i < newCap; i++) Arrays.fill(newW[i], Double.POSITIVE_INFINITY);
        for (int i = 0; i < weight.length; i++) System.arraycopy(weight[i], 0, newW[i], 0, weight.length);
        weight = newW;
    }

    public void addEdge(int i, int j, double w) {
        if (i < 0 || j < 0 || i >= vertices.size() || j >= vertices.size())
            throw new IllegalArgumentException("vertex index out of range");
        weight[i][j] = w;
        if (!directed) weight[j][i] = w;
    }

    public void addEdge(Vertex<T> from, Vertex<T> to, double w) {
        int i = indexOf(from), j = indexOf(to);
        if (i == -1 || j == -1) throw new IllegalArgumentException("vertices must belong to graph");
        addEdge(i, j, w);
    }

    public double getWeight(int i, int j) {
        if (i < 0 || j < 0 || i >= vertices.size() || j >= vertices.size())
            return Double.POSITIVE_INFINITY;
        return weight[i][j];
    }

    public List<Vertex<T>> getVertices() { return Collections.unmodifiableList(vertices); }

    public int size() { return vertices.size(); }

    public int indexOf(Vertex<T> v) { return vertices.indexOf(v); }

    // Convenience: fill complete graph with symmetric weights (useful for TSP demo)
    public void makeCompleteSymmetric(double[][] matrix) {
        int n = matrix.length;
        while (vertices.size() < n) addVertex((T) ("V" + vertices.size()));
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
            addEdge(i, j, matrix[i][j]);
        }
    }

    public void display() {
        System.out.println("--- Graph adjacency matrix ---");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i) + ": ");
            for (int j = 0; j < vertices.size(); j++) {
                double w = weight[i][j];
                String s = Double.isInfinite(w) ? "∞" : String.format("%.2f", w);
                System.out.print(s + (j + 1 < vertices.size() ? ", " : ""));
            }
            System.out.println();
        }
    }
}
