package com.my.ds.code.graphs.disjointSet.impl;

import com.my.ds.code.graphs.disjointSet.contract.GenericDisjointSetInterface;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GenericDisjointSet<T> implements GenericDisjointSetInterface<T> {

    private final Map<T, T> parent = new HashMap<>();
    private final Map<T, Integer> rank = new HashMap<>();

    @Override
    public void makeSet(T x) {
        if (!parent.containsKey(x)) {
            parent.put(x, x);
            rank.put(x, 0);
        }
    }

    @Override
    public T find(T x) {
        if (!parent.containsKey(x)) return null;
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x))); // path compression
        }
        return parent.get(x);
    }

    @Override
    public void union(T a, T b) {
        makeSet(a);
        makeSet(b);

        T ra = find(a);
        T rb = find(b);

        if (ra.equals(rb)) return;

        int rankA = rank.get(ra);
        int rankB = rank.get(rb);

        if (rankA < rankB) {
            parent.put(ra, rb);
        } else if (rankA > rankB) {
            parent.put(rb, ra);
        } else {
            parent.put(rb, ra);
            rank.put(ra, rankA + 1);
        }
    }

    @Override
    public boolean connected(T a, T b) {
        if (!parent.containsKey(a) || !parent.containsKey(b)) return false;
        return find(a).equals(find(b));
    }

    @Override
    public int countSets() {
        Set<T> roots = new HashSet<>();
        for (T x : parent.keySet()) {
            roots.add(find(x));
        }
        return roots.size();
    }

    @Override
    public void displayParents() {
        System.out.println("Parent map:");
        for (Map.Entry<T, T> e : parent.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}