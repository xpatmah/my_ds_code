package com.my.ds.code.graphs.mst.domain;

/* -------------------------
   Edge class
   ------------------------- */
public class Edge<T> {
    private Vertex<T> from;
    private Vertex<T> to;
    private double weight;

    public Edge(Vertex<T> from, Vertex<T> to, double weight) {
        this.from = from; this.to = to; this.weight = weight;
    }

    public Vertex<T> getFrom() { return from; }
    public Vertex<T> getTo() { return to; }
    public double getWeight() { return weight; }

    public void setFrom(Vertex<T> f) { this.from = f; }
    public void setTo(Vertex<T> t) { this.to = t; }
    public void setWeight(double w) { this.weight = w; }

    @Override
    public String toString() {
        return String.format("%s --(%.2f)-- %s", from, weight, to);
    }
}