package com.my.ds.code.queue.demo;

import com.my.ds.code.queue.contract.AbstractCircularQueue;
import com.my.ds.code.queue.impl.circular.arrayBased.CircularQueueArrayBased;
import com.my.ds.code.queue.impl.circular.nodeBased.CircularQueueNodeBased;

public class CircularQueueDemo {
    public static void main(String[] args) {
        System.out.println("=== Array Circular Queue ===");
        AbstractCircularQueue cqArr = new CircularQueueArrayBased(5);
        cqArr.enqueue(10);
        cqArr.enqueue(20);
        cqArr.enqueue(30);
        cqArr.display();
        System.out.println("Dequeue: " + cqArr.dequeue());
        cqArr.display();

        System.out.println("\n=== Linked List Circular Queue ===");
        AbstractCircularQueue cqList = new CircularQueueNodeBased();
        cqList.enqueue(100);
        cqList.enqueue(200);
        cqList.enqueue(300);
        cqList.display();
        System.out.println("Dequeue: " + cqList.dequeue());
        cqList.display();
    }
}