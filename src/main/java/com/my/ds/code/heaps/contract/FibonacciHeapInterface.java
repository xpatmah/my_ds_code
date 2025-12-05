package com.my.ds.code.heaps.contract;

import com.my.ds.code.heaps.domain.FibNode;
import com.my.ds.code.heaps.impl.FibonacciHeap;

public interface FibonacciHeapInterface<T extends Comparable<T>> {
    FibNode<T> insert(T key);
    T findMin();
    T extractMin();
    void decreaseKey(FibNode<T> node, T newKey);
    void delete(FibNode<T> node);
    void merge(FibonacciHeap<T> other);
    boolean isEmpty();
    int size();
}