package com.my.ds.code.hashing.contract;

public interface SimpleHashMap<K, V> {
    void put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    int size();
    void display(); // debug view
}
