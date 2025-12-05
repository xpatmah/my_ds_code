package com.my.ds.code.cache.lru.impl;

import com.my.ds.code.cache.lru.contract.CacheInterface;
import com.my.ds.code.cache.lru.domain.DoublyNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Generic LRU Cache implementation.
 *
 * Implementation notes:
 *  - Uses a HashMap<K, DoublyNode<K,V>> for O(1) lookup.
 *  - Uses a doubly linked list with head (most recently used) and tail (least).
 *  - On get/put access, node is moved to head.
 *  - When capacity is exceeded, tail node is evicted.
 */
public class LRUCache<K, V> implements CacheInterface<K, V> {
    private final int capacity;
    private final Map<K, DoublyNode<K, V>> map;
    // sentinel nodes for head and tail to simplify edge operations
    private final DoublyNode<K, V> head;
    private final DoublyNode<K, V> tail;
    private int size;

    public LRUCache(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        this.capacity = capacity;
        this.map = new HashMap<>(capacity * 2);
        this.head = new DoublyNode<>(null, null); // dummy head (MRU comes after head)
        this.tail = new DoublyNode<>(null, null); // dummy tail (LRU comes before tail)
        head.setNext(tail);
        tail.setPrev(head);
        this.size = 0;
    }

    @Override
    public V get(K key) {
        DoublyNode<K, V> node = map.get(key);
        if (node == null) return null;
        // move to head (most recently used)
        moveToHead(node);
        return node.getValue();
    }

    @Override
    public void put(K key, V value) {
        DoublyNode<K, V> node = map.get(key);
        if (node != null) {
            // update value and move to head
            node.setValue(value);
            moveToHead(node);
            return;
        }

        // new node
        DoublyNode<K, V> newNode = new DoublyNode<>(key, value);
        map.put(key, newNode);
        addNodeAfterHead(newNode);
        size++;

        if (size > capacity) {
            // remove LRU node (node before tail)
            DoublyNode<K, V> lru = tail.getPrev();
            removeNode(lru);
            map.remove(lru.getKey());
            size--;
        }
    }

    @Override
    public V remove(K key) {
        DoublyNode<K, V> node = map.remove(key);
        if (node == null) return null;
        removeNode(node);
        size--;
        return node.getValue();
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void clear() {
        // reset list
        head.setNext(tail);
        tail.setPrev(head);
        map.clear();
        size = 0;
    }

    // ------------------ internal helper operations on doubly linked list ------------------

    // add node right after head (most recently used position)
    private void addNodeAfterHead(DoublyNode<K, V> node) {
        DoublyNode<K, V> next = head.getNext();
        node.setPrev(head);
        node.setNext(next);
        head.setNext(node);
        next.setPrev(node);
    }

    // remove node from current position
    private void removeNode(DoublyNode<K, V> node) {
        DoublyNode<K, V> prev = node.getPrev();
        DoublyNode<K, V> next = node.getNext();
        if (prev != null) prev.setNext(next);
        if (next != null) next.setPrev(prev);
        node.setPrev(null);
        node.setNext(null);
    }

    // move existing node to head (MRU)
    private void moveToHead(DoublyNode<K, V> node) {
        removeNode(node);
        addNodeAfterHead(node);
    }

    // convenience debug: print keys from most recent to least recent
    public void display() {
        System.out.print("LRU cache (MRU -> LRU): ");
        DoublyNode<K, V> cur = head.getNext();
        while (cur != tail) {
            System.out.print("[" + cur.getKey() + "=" + cur.getValue() + "] ");
            cur = cur.getNext();
        }
        System.out.println();
    }

    // expose capacity
    public int capacity() { return capacity; }
}