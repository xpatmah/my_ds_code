package com.my.ds.code.graphs.disjointSet.demo;

import com.my.ds.code.graphs.disjointSet.impl.DisjointSet;

public class DisjointSetDemo {

    // Demo
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(7);

        ds.union(0, 1);
        ds.union(1, 2);
        ds.union(3, 4);
        ds.union(5, 6);

        ds.displayParents();
        System.out.println("Connected(0, 2)? " + ds.connected(0, 2));
        System.out.println("Connected(0, 5)? " + ds.connected(0, 5));

        ds.union(2, 5);
        System.out.println("Connected(0, 5)? " + ds.connected(0, 5));

        System.out.println("Number of disjoint sets: " + ds.countSets());
    }
}
