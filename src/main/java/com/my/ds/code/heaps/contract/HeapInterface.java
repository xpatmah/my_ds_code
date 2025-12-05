package com.my.ds.code.heaps.contract;

import java.util.List;

/**
 * Heap interface declaring the standard heap operations.
 */
public interface HeapInterface<T extends Comparable<T>> {
    void insert(T value);
    T peek();                 // returns root element
    T extract();              // remove + return root
    boolean remove(T value);  // remove arbitrary element
    int size();
    boolean isEmpty();
    void clear();
    List<T> levelOrder();
}