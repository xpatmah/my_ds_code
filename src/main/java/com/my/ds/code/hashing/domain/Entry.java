package com.my.ds.code.hashing.domain;

public class Entry<K,V> {
        public K key;
        public V value;
        public boolean deleted;
        public Entry(K k, V v) { key = k; value = v; deleted = false; }
    }