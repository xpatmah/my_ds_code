package com.my.ds.code.graphs.disjointSet.demo;

import com.my.ds.code.graphs.disjointSet.impl.DisjointSet;
import com.my.ds.code.graphs.disjointSet.impl.GenericDisjointSet;

public class GenericDisjointSetDemo {

    // Demo
    public static void main(String[] args) {
        GenericDisjointSet<String> gds = new GenericDisjointSet<>();

        gds.union("A", "B");
        gds.union("B", "C");
        gds.union("D", "E");

        gds.displayParents();

        System.out.println("Connected(A, C)? " + gds.connected("A", "C"));
        System.out.println("Connected(A, E)? " + gds.connected("A", "E"));

        gds.union("C", "E");

        System.out.println("Connected(A, E)? " + gds.connected("A", "E"));
        System.out.println("Number of sets: " + gds.countSets());
    }
}
