package com.my.ds.code.queue.demo;

import com.my.ds.code.queue.contract.AbstractCircularDeque;
import com.my.ds.code.queue.impl.doubleEndedOrDeQue.arrayBased.CircularDequeArrayBased;
import com.my.ds.code.queue.impl.doubleEndedOrDeQue.nodeBased.CircularDequeNodeBased;

public class CircularDeQueueDemo {
    public static void main(String[] args) {
        System.out.println("\n=== Circular Deque (Array) ===");
        AbstractCircularDeque deque = new CircularDequeArrayBased(5);
        deque.enqueue(1);
        deque.enqueue(2);
        deque.addFront(0);
        deque.display();
        System.out.println("removeRear: " + deque.removeRear());
        deque.display();

        AbstractCircularDeque cirListDeque = new CircularDequeNodeBased();
        cirListDeque.addFront(10);
        cirListDeque.addFront(20);
        cirListDeque.enqueue(5);
        cirListDeque.display();  // 20 10 5

        System.out.println("removeRear: " + cirListDeque.removeRear());
        cirListDeque.display();  // 20 10

        System.out.println("dequeue: " + cirListDeque.dequeue());
        cirListDeque.display();  // 10
    }
}