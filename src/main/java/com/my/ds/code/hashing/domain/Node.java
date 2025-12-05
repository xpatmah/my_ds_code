package com.my.ds.code.hashing.domain;

public class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
        public Node(K k, V v) { key = k; value = v; next = null; }
        public K getKey() { return key; }
        public V getValue() { return value; }
        public void setValue(V v) { value = v; }
        public Node<K,V> getNext() { return next; }
        public void setNext(Node<K,V> n) { next = n; }
    }