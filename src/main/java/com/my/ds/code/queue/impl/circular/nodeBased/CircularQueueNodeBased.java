package com.my.ds.code.queue.impl.circular.nodeBased;

import com.my.ds.code.queue.contract.AbstractCircularQueue;
import com.my.ds.code.queue.domain.Node;

public class CircularQueueNodeBased extends AbstractCircularQueue {
    private Node front, rear;
    private int size;

    public CircularQueueNodeBased() {
        front = rear = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() { return front == null; }

    @Override
    public boolean isFull() { return false; } // dynamic size

    @Override
    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (front == null) {
            front = rear = newNode;
            rear.setNext(front);
        } else {
            rear.setNext(newNode);
            rear = newNode;
            rear.setNext(front);
        }
        size++;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow!");
            return -1;
        }
        int val;
        if (front == rear) {
            val = front.getData();
            front = rear = null;
        } else {
            val = front.getData();
            front = front.getNext();
            rear.setNext(front);
        }
        size--;
        return val;
    }

    @Override
    public int peek() {
        if (isEmpty()) return -1;
        return front.getData();
    }

    @Override
    public int size() { return size; }

    @Override
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        Node temp = front;
        System.out.print("CircularQueueLinkedList (front -> rear): ");
        do {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        } while (temp != front);
        System.out.println();
    }
}