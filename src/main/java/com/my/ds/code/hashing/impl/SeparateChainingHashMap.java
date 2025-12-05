package com.my.ds.code.hashing.impl;

import com.my.ds.code.hashing.contract.SimpleHashMap;
import com.my.ds.code.hashing.domain.Node;

import java.util.Objects;

public class SeparateChainingHashMap<K, V> implements SimpleHashMap<K, V> {

    private Node<K,V>[] buckets;
    private int capacity;
    private int size;
    private static final int DEFAULT_CAP = 16;
    private static final double LOAD_FACTOR = 1.0; // avg chain length threshold

    @SuppressWarnings("unchecked")
    public SeparateChainingHashMap(int cap) {
        capacity = Math.max(4, cap);
        buckets = (Node<K,V>[]) new Node[capacity];
        size = 0;
    }
    public SeparateChainingHashMap() { this(DEFAULT_CAP); }

    private int hashIndex(Object key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    @Override
    public void put(K key, V value) {
        int idx = hashIndex(key);
        Node<K,V> head = buckets[idx];
        for (Node<K,V> cur = head; cur != null; cur = cur.getNext()) {
            if (Objects.equals(cur.getKey(), key)) {
                cur.setValue(value);
                return;
            }
        }
        Node<K,V> n = new Node<>(key, value);
        n.setNext(head);
        buckets[idx] = n;
        size++;
        if ((double)size / capacity > LOAD_FACTOR) resize();
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K,V>[] old = buckets;
        capacity *= 2;
        buckets = (Node<K,V>[]) new Node[capacity];
        size = 0;
        for (Node<K,V> head : old) {
            for (Node<K,V> cur = head; cur != null; cur = cur.getNext()) put(cur.getKey(), cur.getValue());
        }
    }

    @Override
    public V get(K key) {
        int idx = hashIndex(key);
        for (Node<K,V> cur = buckets[idx]; cur != null; cur = cur.getNext()) {
            if (Objects.equals(cur.getKey(), key)) return cur.getValue();
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int idx = hashIndex(key);
        Node<K,V> cur = buckets[idx];
        Node<K,V> prev = null;
        while (cur != null) {
            if (Objects.equals(cur.getKey(), key)) {
                if (prev == null) buckets[idx] = cur.getNext();
                else prev.setNext(cur.getNext());
                size--;
                return cur.getValue();
            }
            prev = cur;
            cur = cur.getNext();
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) { return get(key) != null; }

    @Override
    public int size() { return size; }

    @Override
    public void display() {
        System.out.println("SeparateChainingHashMap (capacity " + capacity + "):");
        for (int i = 0; i < capacity; i++) {
            System.out.print(" [" + i + "]: ");
            for (Node<K,V> cur = buckets[i]; cur != null; cur = cur.getNext()) {
                System.out.print(cur.getKey() + "=" + cur.getValue() + " -> ");
            }
            System.out.println("null");
        }
    }
}