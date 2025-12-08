package com.my.ds.code.graphs.disjointSet.contract;

public interface DisjointSetInterface {
    void union(int x, int y);
    int find(int x);
    boolean connected(int x, int y);
    int countSets();
    void displayParents();
}