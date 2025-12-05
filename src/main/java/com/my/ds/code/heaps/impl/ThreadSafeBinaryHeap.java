package com.my.ds.code.heaps.impl;

import com.my.ds.code.heaps.contract.HeapInterfaceTS;
import com.my.ds.code.heaps.domain.HeapNodeTS;
import com.my.ds.code.heaps.domain.HeapType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBinaryHeap<T extends Comparable<T>> implements HeapInterfaceTS<T> {
    private final List<HeapNodeTS<T>> heap;
    private final HeapType kind;
    private final ReentrantLock lock = new ReentrantLock();

    public ThreadSafeBinaryHeap(HeapType kind) {
        this.kind = kind;
        this.heap = new ArrayList<>();
    }

    private boolean compare(T a, T b) {
        if (kind == HeapType.MIN) return a.compareTo(b) < 0;
        else return a.compareTo(b) > 0;
    }

    @Override
    public void insert(T value) {
        lock.lock();
        try {
            HeapNodeTS<T> node = new HeapNodeTS<>(value);
            heap.add(node);
            heapifyUp(heap.size() - 1);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T peek() {
        lock.lock();
        try {
            if (heap.isEmpty()) return null;
            return heap.get(0).getValue();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T extract() {
        lock.lock();
        try {
            if (heap.isEmpty()) return null;
            T rootVal = heap.get(0).getValue();
            HeapNodeTS<T> last = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, last);
                heapifyDown(0);
            }
            return rootVal;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean remove(T value) {
        lock.lock();
        try {
            int idx = -1;
            for (int i = 0; i < heap.size(); i++) {
                if (heap.get(i).getValue().equals(value)) {
                    idx = i;
                    break;
                }
            }
            if (idx == -1) return false;
            HeapNodeTS<T> last = heap.remove(heap.size() - 1);
            if (idx < heap.size()) {
                heap.set(idx, last);
                heapifyUp(idx);
                heapifyDown(idx);
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return heap.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return heap.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            heap.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<T> levelOrder() {
        lock.lock();
        try {
            List<T> res = new ArrayList<>();
            for (HeapNodeTS<T> n : heap) res.add(n.getValue());
            return res;
        } finally {
            lock.unlock();
        }
    }

    private void heapifyUp(int idx) {
        while (idx > 0) {
            int p = (idx - 1) / 2;
            if (compare(heap.get(idx).getValue(), heap.get(p).getValue())) {
                swap(idx, p);
                idx = p;
            } else break;
        }
    }

    private void heapifyDown(int idx) {
        int n = heap.size();
        while (true) {
            int left = 2 * idx + 1;
            int right = left + 1;
            int best = idx;
            if (left < n && compare(heap.get(left).getValue(), heap.get(best).getValue())) best = left;
            if (right < n && compare(heap.get(right).getValue(), heap.get(best).getValue())) best = right;
            if (best != idx) {
                swap(idx, best);
                idx = best;
            } else break;
        }
    }

    private void swap(int i, int j) {
        HeapNodeTS<T> tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }
}