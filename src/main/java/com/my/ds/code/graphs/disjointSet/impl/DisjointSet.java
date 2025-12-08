package com.my.ds.code.graphs.disjointSet.impl;

import com.my.ds.code.graphs.disjointSet.contract.DisjointSetInterface;

public class DisjointSet implements DisjointSetInterface {
    private int[] parent;
    private int[] rank;
    private int count; // number of sets

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i; // each node is its own parent
            rank[i] = 0;
        }
    }

    @Override
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    @Override
    public void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if (rx == ry) return; // already in same set

        // union by rank
        if (rank[rx] < rank[ry]) {
            parent[rx] = ry;
        } else if (rank[rx] > rank[ry]) {
            parent[ry] = rx;
        } else {
            parent[ry] = rx;
            rank[rx]++;
        }

        count--;
    }

    @Override
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    @Override
    public int countSets() {
        return count;
    }

    @Override
    public void displayParents() {
        System.out.print("Parent array: ");
        for (int p : parent) System.out.print(p + " ");
        System.out.println();
    }
}