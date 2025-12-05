package com.my.ds.code.heaps.impl;

import com.my.ds.code.heaps.contract.HeapInterface;
import com.my.ds.code.heaps.domain.HeapNode;
import com.my.ds.code.heaps.domain.HeapType;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary Heap implementation (both MinHeap & MaxHeap)
 * backed by ArrayList<HeapNode<T>>.
 */
public class BinaryHeap<T extends Comparable<T>> implements HeapInterface<T> {

    private final HeapType type;
    private final List<HeapNode<T>> heap;

    public BinaryHeap(HeapType type) {
        this.type = type;
        this.heap = new ArrayList<>();
    }

    private boolean compare(T a, T b) {
        if (type == HeapType.MIN) {
            return a.compareTo(b) < 0;     // a < b
        } else {
            return a.compareTo(b) > 0;     // a > b
        }
    }

    @Override
    public void insert(T value) {
        HeapNode<T> node = new HeapNode<>(value);
        heap.add(node);
        heapifyUp(heap.size() - 1);
    }

    @Override
    public T peek() {
        if (heap.isEmpty()) return null;
        return heap.get(0).getValue();
    }

    @Override
    public T extract() {
        if (heap.isEmpty()) return null;

        T rootValue = heap.get(0).getValue();
        HeapNode<T> last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return rootValue;
    }

    @Override
    public boolean remove(T value) {
        if (heap.isEmpty()) return false;

        int index = -1;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).getValue().equals(value)) {
                index = i;
                break;
            }
        }

        if (index == -1) return false;

        HeapNode<T> last = heap.remove(heap.size() - 1);

        if (index < heap.size()) {
            heap.set(index, last);
            heapifyUp(index);
            heapifyDown(index);
        }
        return true;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIdx = (index - 1) / 2;
            T cur = heap.get(index).getValue();
            T parent = heap.get(parentIdx).getValue();

            if (compare(cur, parent)) {
                swap(index, parentIdx);
                index = parentIdx;
            } else break;
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();

        while (true) {
            int leftIdx = 2 * index + 1;
            int rightIdx = 2 * index + 2;
            int smallestOrLargest = index;

            if (leftIdx < size &&
                compare(heap.get(leftIdx).getValue(), heap.get(smallestOrLargest).getValue())) {
                smallestOrLargest = leftIdx;
            }

            if (rightIdx < size &&
                compare(heap.get(rightIdx).getValue(), heap.get(smallestOrLargest).getValue())) {
                smallestOrLargest = rightIdx;
            }

            if (smallestOrLargest != index) {
                swap(index, smallestOrLargest);
                index = smallestOrLargest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        HeapNode<T> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override
    public int size() { return heap.size(); }

    @Override
    public boolean isEmpty() { return heap.isEmpty(); }

    @Override
    public void clear() { heap.clear(); }

    @Override
    public List<T> levelOrder() {
        List<T> res = new ArrayList<>();
        for (HeapNode<T> node : heap) res.add(node.getValue());
        return res;
    }

    /** Utility: build heap from list */
    public void buildHeap(List<T> values) {
        clear();
        for (T v : values) heap.add(new HeapNode<>(v));
        for (int i = heap.size() / 2; i >= 0; i--) heapifyDown(i);
    }
}