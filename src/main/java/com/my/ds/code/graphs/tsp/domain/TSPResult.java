package com.my.ds.code.graphs.tsp.domain;

import java.util.List;
/* -------------------------
   TSP result container
   ------------------------- */
public class TSPResult<T> {
    public final List<Vertex<T>> tour; // ordered tour vertices (starts at 0-th in list and returns to it implicitly)
    public final double cost;
    public TSPResult(List<Vertex<T>> tour, double cost) { this.tour = tour; this.cost = cost; }
    @Override public String toString() {
        return "cost=" + cost + ", tour=" + tour;
    }
}