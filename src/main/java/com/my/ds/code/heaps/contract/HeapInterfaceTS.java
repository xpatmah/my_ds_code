package com.my.ds.code.heaps.contract;

import java.util.List;

public interface HeapInterfaceTS<T extends Comparable<T>> {
    void insert(T value);
    T peek();
    T extract();
    boolean remove(T value);
    int size();
    boolean isEmpty();
    void clear();
    List<T> levelOrder();
}