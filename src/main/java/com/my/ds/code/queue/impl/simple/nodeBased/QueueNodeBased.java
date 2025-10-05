package com.my.ds.code.queue.impl.simple.nodeBased;

import com.my.ds.code.queue.contract.AbstractSimpleQueue;
import com.my.ds.code.queue.domain.Node;

// Queue implementation using Linked List
public class QueueNodeBased extends AbstractSimpleQueue {
    private Node front, rear;
    private int size;

    public QueueNodeBased() {
        front = rear = null;
        size = 0;
    }

    // Enqueue
    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (rear == null) { // empty queue
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    // Dequeue
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow!");
            return -1;
        }
        int val = front.getData();
        front = front.getNext();
        if (front == null) rear = null; // if queue becomes empty
        size--;
        return val;
    }

    // Peek
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return front.getData();
    }

    // Utility methods
    public boolean isEmpty() { return front == null; }

    @Override
    public boolean isFull() {
        return false; // dynamically growing so can't be full
    }

    public int size() { return size; }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue (front -> rear): ");
        Node temp = front;
        while (temp != null) {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }
}