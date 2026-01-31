package com.my.ds.code.heaps.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Generic Priority Queue implemented with a binary heap (array-backed).
 * - If a Comparator is provided, it will be used.
 * - Otherwise, elements must implement Comparable<T> and natural ordering will be used.
 *
 * Complexity:
 *  - offer/insert: O(log n)
 *  - poll/extract: O(log n)
 *  - peek: O(1)
 */
public class GenericPriorityQueue<T> {
    private Object[] heap;            // array backing: stores T but in Object[] due to generics
    private int size;
    private Comparator<? super T> cmp;

    private static final int DEFAULT_CAPACITY = 16;

    public GenericPriorityQueue() {
        this(null, DEFAULT_CAPACITY);
    }

    public GenericPriorityQueue(int initialCapacity) {
        this(null, initialCapacity);
    }

    public GenericPriorityQueue(Comparator<? super T> comparator) {
        this(comparator, DEFAULT_CAPACITY);
    }

    public GenericPriorityQueue(Comparator<? super T> comparator, int initialCapacity) {
        if (initialCapacity <= 0) initialCapacity = DEFAULT_CAPACITY;
        this.heap = new Object[initialCapacity];
        this.size = 0;
        this.cmp = comparator;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public void clear() {
        Arrays.fill(heap, 0, size, null);
        size = 0;
    }

    /**
     * Insert element into the heap (offer).
     */
    public void offer(T value) {
        if (value == null) throw new NullPointerException("Null elements not supported");
        ensureCapacity(size + 1);
        heap[size] = value;
        siftUp(size++);
    }

    /**
     * Peek at root (min or max depending on comparator).
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (size == 0) return null;
        return (T) heap[0];
    }

    /**
     * Extract root (poll). Returns null if empty.
     */
    @SuppressWarnings("unchecked")
    public T poll() {
        if (size == 0) return null;
        T result = (T) heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        if (size > 0) siftDown(0);
        return result;
    }

    /**
     * Remove and throw if empty (convenience)
     */
    public T remove() {
        T val = poll();
        if (val == null) throw new NoSuchElementException();
        return val;
    }

    // ----------------- internal helpers -----------------

    @SuppressWarnings("unchecked")
    private int compare(int i, int j) {
        T a = (T) heap[i];
        T b = (T) heap[j];
        if (cmp != null) return cmp.compare(a, b);
        // no comparator: use natural ordering
        return ((Comparable<? super T>) a).compareTo(b);
    }

    @SuppressWarnings("unchecked")
    private int compareVal(T a, T b) {
        if (cmp != null) return cmp.compare(a, b);
        return ((Comparable<? super T>) a).compareTo(b);
    }

    private void swap(int i, int j) {
        Object tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void siftUp(int idx) {
        int cur = idx;
        Object val = heap[cur];
        while (cur > 0) {
            int parent = (cur - 1) >>> 1;
            // if heap[parent] <= heap[cur] (for min-heap), stop
            if (compareValAt(parent, val) <= 0) break;
            heap[cur] = heap[parent];
            cur = parent;
        }
        heap[cur] = val;
    }

    @SuppressWarnings("unchecked")
    private int compareValAt(int idx, Object valObj) {
        T val = (T) valObj;
        T atIdx = (T) heap[idx];
        if (cmp != null) return cmp.compare(atIdx, val);
        return ((Comparable<? super T>) atIdx).compareTo(val);
    }

    private void siftDown(int idx) {
        int cur = idx;
        Object val = heap[cur];
        int half = size >>> 1; // nodes with children are in [0..half-1]
        while (cur < half) {
            int left = (cur << 1) + 1;
            int right = left + 1;
            int smallest = left;
            if (right < size && compare(right, left) < 0) smallest = right;
            if (compareValAt(smallest, val) >= 0) break;
            heap[cur] = heap[smallest];
            cur = smallest;
        }
        heap[cur] = val;
    }

    private void ensureCapacity(int required) {
        if (required <= heap.length) return;
        int newCap = heap.length + (heap.length >> 1);
        if (newCap < required) newCap = required;
        heap = Arrays.copyOf(heap, newCap);
    }

    // Optional utility: build heap from array
    public static <T> GenericPriorityQueue<T> fromArray(T[] arr, Comparator<? super T> comparator) {
        GenericPriorityQueue<T> pq = new GenericPriorityQueue<>(comparator, Math.max(arr.length, DEFAULT_CAPACITY));
        for (T e : arr) pq.heap[pq.size++] = e;
        // heapify
        for (int i = (pq.size >>> 1) - 1; i >= 0; i--) pq.siftDown(i);
        return pq;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GenericPriorityQueue[size=").append(size).append(", elements=[");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i]);
            if (i + 1 < size) sb.append(", ");
        }
        sb.append("]]");
        return sb.toString();
    }
}
