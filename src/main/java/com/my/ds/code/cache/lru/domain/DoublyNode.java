package com.my.ds.code.cache.lru.domain;

/**
 * Doubly-linked node used by the LRU cache.
 * Encapsulates key/value and prev/next pointers with getters/setters.
 */
public class DoublyNode<K, V> {
    private K key;
    private V value;
    private DoublyNode<K, V> prev;
    private DoublyNode<K, V> next;

    public DoublyNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    // getters
    public K getKey() { return key; }
    public V getValue() { return value; }
    public DoublyNode<K, V> getPrev() { return prev; }
    public DoublyNode<K, V> getNext() { return next; }

    // setters
    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
    public void setPrev(DoublyNode<K, V> prev) { this.prev = prev; }
    public void setNext(DoublyNode<K, V> next) { this.next = next; }
}