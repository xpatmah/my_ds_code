package com.my.ds.code.graphs.shortestPath.domain;

// small helper pair for PQ
public class Pair<V> {
    final V vertex;
    final double dist;
    public Pair(V v, double d) { vertex = v; dist = d; }
    public V getVertex() {
        return vertex;
    }

    public double getDist() {
        return dist;
    }
}