package com.my.ds.code.graphs.disjointSet.contract;

public interface GenericDisjointSetInterface<T> {
    void makeSet(T x);
    T find(T x);
    void union(T a, T b);
    boolean connected(T a, T b);
    int countSets();
    void displayParents();
}