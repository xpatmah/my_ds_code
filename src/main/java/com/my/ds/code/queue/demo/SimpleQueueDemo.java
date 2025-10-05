package com.my.ds.code.queue.demo;

import com.my.ds.code.queue.impl.simple.arrayBased.QueueArrayBased;
import com.my.ds.code.queue.impl.simple.nodeBased.QueueNodeBased;

public class SimpleQueueDemo {

    public static void main(String[] args) {
        System.out.println("=== Array Queue ===");
        QueueArrayBased aq = new QueueArrayBased(5);
        aq.enqueue(10);
        aq.enqueue(20);
        aq.enqueue(30);
        aq.display();
        System.out.println("Dequeue: " + aq.dequeue());
        aq.display();

        System.out.println("\n=== Linked List Queue ===");
        QueueNodeBased lq = new QueueNodeBased();
        lq.enqueue(100);
        lq.enqueue(200);
        lq.enqueue(300);
        lq.display();
        System.out.println("Dequeue: " + lq.dequeue());
        lq.display();
    }
}