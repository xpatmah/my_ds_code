package com.my.ds.code.hashing.impl;

import com.my.ds.code.hashing.contract.SimpleHashMap;
import com.my.ds.code.hashing.domain.Entry;

import java.util.Objects;

/* ----------------------------
   3) Open Addressing - Quadratic Probing
   ---------------------------- */
public class QuadraticProbingHashMap<K, V> implements SimpleHashMap<K, V> {

    private Entry<K,V>[] table;
    private int capacity;
    private int size;
    private static final int DEFAULT_CAP = 17; // prime helps with probing
    private static final double MAX_LOAD = 0.6;

    @SuppressWarnings("unchecked")
    public QuadraticProbingHashMap(int cap) {
        capacity = Math.max(5, cap | 1); // ensure odd
        table = (Entry<K,V>[]) new Entry[capacity];
        size = 0;
    }
    public QuadraticProbingHashMap() { this(DEFAULT_CAP); }

    private int hash(Object key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    @Override
    public void put(K key, V value) {
        if ((double)(size+1)/capacity > MAX_LOAD) resize();
        int h = hash(key);
        int firstDeleted = -1;
        for (int i = 0; i < capacity; i++) {
            int pos = (h + i*i) % capacity;
            Entry<K,V> e = table[pos];
            if (e == null) {
                if (firstDeleted != -1) pos = firstDeleted;
                table[pos] = new Entry<>(key, value);
                size++;
                return;
            } else if (e.deleted) {
                if (firstDeleted == -1) firstDeleted = pos;
            } else if (Objects.equals(e.key, key)) {
                e.value = value; return;
            }
        }
    }

    @Override
    public V get(K key) {
        int h = hash(key);
        for (int i = 0; i < capacity; i++) {
            int pos = (h + i*i) % capacity;
            Entry<K,V> e = table[pos];
            if (e == null) return null;
            if (!e.deleted && Objects.equals(e.key, key)) return e.value;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int h = hash(key);
        for (int i = 0; i < capacity; i++) {
            int pos = (h + i*i) % capacity;
            Entry<K,V> e = table[pos];
            if (e == null) return null;
            if (!e.deleted && Objects.equals(e.key, key)) {
                e.deleted = true;
                size--;
                return e.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) { return get(key) != null; }

    @Override
    public int size() { return size; }

    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K,V>[] old = table;
        capacity = capacity * 2 + 1; // keep odd
        table = (Entry<K,V>[]) new Entry[capacity];
        size = 0;
        for (Entry<K,V> e : old) if (e != null && !e.deleted) put(e.key, e.value);
    }

    @Override
    public void display() {
        System.out.println("QuadraticProbingHashMap (capacity " + capacity + "):");
        for (int i = 0; i < capacity; i++) {
            Entry<K,V> e = table[i];
            if (e == null) System.out.println(" ["+i+"]: null");
            else System.out.println(" ["+i+"]: " + (e.deleted ? "<TOMBSTONE>" : e.key + "=" + e.value));
        }
    }
}
