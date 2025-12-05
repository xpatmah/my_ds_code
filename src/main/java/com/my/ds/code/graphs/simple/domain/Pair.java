package com.my.ds.code.graphs.simple.domain;

public class Pair<T> {

    private final Vertex<T> vertex;
    private final double dist;

    public Pair(Vertex<T> vertex, double d) {
        this.vertex=vertex;this.dist=d;
    }

    public Vertex<T> getVertex() {
        return vertex;
    }

    public double getDist() {
        return dist;
    }
}