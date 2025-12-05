package com.my.ds.code.heaps.demo;

import com.my.ds.code.heaps.domain.FibNode;
import com.my.ds.code.heaps.impl.FibonacciHeap;

public class FibHeapDemo {
    // -------------------------
    // Demo
    // -------------------------
    public static void main(String[] args) {
        FibonacciHeap<Integer> fh = new FibonacciHeap<>();
        FibNode<Integer> n1 = fh.insert(10);
        FibNode<Integer> n2 = fh.insert(3);
        FibNode<Integer> n3 = fh.insert(15);
        FibNode<Integer> n4 = fh.insert(7);

        System.out.println("Min: " + fh.findMin()); // 3
        fh.decreaseKey(n3, 2);
        System.out.println("Min after decreaseKey 15->2: " + fh.findMin()); // 2

        System.out.println("ExtractMin: " + fh.extractMin()); // 2
        System.out.println("New Min: " + fh.findMin());

        // delete via handle (note: delete requires node handle)
        fh.delete(n1); // deletes node with key 10
        System.out.println("Size after delete: " + fh.size());

        // Insert more and extract all
        fh.insert(20); fh.insert(1); fh.insert(9);
        while (!fh.isEmpty()) System.out.print(fh.extractMin() + " ");
        System.out.println();
    }

}
