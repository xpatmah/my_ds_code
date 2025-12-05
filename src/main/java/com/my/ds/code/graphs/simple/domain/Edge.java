package com.my.ds.code.graphs.simple.domain;

/* --------------------
   Edge class
   -------------------- */
public class Edge<T> {
    private Vertex<T> from;
    private Vertex<T> to;
    private double weight;

    public Edge(Vertex<T> from, Vertex<T> to) {
        this(from, to, 1.0);
    }

    public Edge(Vertex<T> from, Vertex<T> to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Vertex<T> getFrom() { return from; }
    public Vertex<T> getTo() { return to; }
    public double getWeight() { return weight; }

    public void setFrom(Vertex<T> f) { this.from = f; }
    public void setTo(Vertex<T> t) { this.to = t; }
    public void setWeight(double w) { this.weight = w; }

    @Override
    public String toString() {
        return String.format("(%s -> %s, w=%.2f)", from, to, weight);
    }
}
