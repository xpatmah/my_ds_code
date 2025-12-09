package com.my.ds.code.graphs.shortestPath.domain;

import java.util.Objects;

/* --------------------
   Vertex class
   -------------------- */
public class Vertex<T> {
    private final T label;
    public Vertex(T label) { this.label = label; }
    public T getLabel() { return label; }
    @Override public String toString() { return String.valueOf(label); }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex<?> v = (Vertex<?>) o;
        return Objects.equals(label, v.label);
    }
    @Override public int hashCode() { return Objects.hashCode(label); }
}