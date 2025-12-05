package com.my.ds.code.heaps.demo;

import com.my.ds.code.heaps.domain.HeapType;
import com.my.ds.code.heaps.impl.BinaryHeap;

/**
 * Demo of MinHeap + MaxHeap
 */
public class BinaryHeapDemo {
    public static void main(String[] args) {

        System.out.println("=== MIN HEAP ===");
        BinaryHeap<Integer> minHeap = new BinaryHeap<>(HeapType.MIN);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(10);
        minHeap.insert(1);
        System.out.println("Level-order: " + minHeap.levelOrder());
        System.out.println("Extract min: " + minHeap.extract());
        System.out.println("After extract: " + minHeap.levelOrder());
        System.out.println("Min (peek): " + minHeap.peek());

        System.out.println("\n=== MAX HEAP ===");
        BinaryHeap<Integer> maxHeap = new BinaryHeap<>(HeapType.MAX);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(10);
        maxHeap.insert(1);
        System.out.println("Level-order: " + maxHeap.levelOrder());
        System.out.println("Extract max: " + maxHeap.extract());
        System.out.println("After extract: " + maxHeap.levelOrder());
        System.out.println("Max (peek): " + maxHeap.peek());

        System.out.println("\n=== Removal Demo ===");
        maxHeap.remove(3);
        System.out.println("After removing 3: " + maxHeap.levelOrder());
    }
}
