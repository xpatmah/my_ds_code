package com.my.ds.code.hashing.demo;

import com.my.ds.code.hashing.contract.SimpleHashMap;
import com.my.ds.code.hashing.impl.DoubleHashingHashMap;
import com.my.ds.code.hashing.impl.LinearProbingHashMap;
import com.my.ds.code.hashing.impl.QuadraticProbingHashMap;
import com.my.ds.code.hashing.impl.SeparateChainingHashMap;

/* ----------------------------
   Demo: exercise all maps
   ---------------------------- */
public class CollisionResolutionDemo {
    public static void main(String[] args) {
        System.out.println("=== Separate Chaining ===");
        SimpleHashMap<String,Integer> sch = new SeparateChainingHashMap<>();
        runSample(sch);

        System.out.println("\n=== Linear Probing ===");
        SimpleHashMap<String,Integer> lph = new LinearProbingHashMap<>();
        runSample(lph);

        System.out.println("\n=== Quadratic Probing ===");
        SimpleHashMap<String,Integer> qph = new QuadraticProbingHashMap<>();
        runSample(qph);

        System.out.println("\n=== Double Hashing ===");
        SimpleHashMap<String,Integer> dh = new DoubleHashingHashMap<>();
        runSample(dh);
    }

    private static void runSample(SimpleHashMap<String,Integer> map) {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        map.put("ten", 10);

        map.display();
        System.out.println("get(\"five\") = " + map.get("five"));
        System.out.println("remove(\"three\") = " + map.remove("three"));
        System.out.println("containsKey(\"three\") = " + map.containsKey("three"));
        System.out.println("size = " + map.size());
        map.put("three", 33);
        System.out.println("after re-put three:");
        map.display();
    }
}