package com.my.ds.code.lru.contract;

/**
 * Simple cache interface for LRUCache
 */
public interface CacheInterface<K, V> {
    V get(K key);
    void put(K key, V value);
    V remove(K key);
    int size();
    boolean isEmpty();
    void clear();
}