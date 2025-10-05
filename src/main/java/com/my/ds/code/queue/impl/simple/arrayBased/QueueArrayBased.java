package com.my.ds.code.queue.impl.simple.arrayBased;

import com.my.ds.code.queue.contract.AbstractSimpleQueue;

public class QueueArrayBased extends AbstractSimpleQueue {
    private int[] arr;
    private int front, rear, size, capacity;

    public QueueArrayBased(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Enqueue
    @Override
    public void enqueue(int data) {
        if (isFull()) {
            System.out.println("Queue Overflow!");
            return;
        }
        rear = (rear + 1) % capacity;  // circular wrap
        arr[rear] = data;
        size++;
    }

    // Dequeue
    @Override
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow!");
            return -1;
        }
        int data = arr[front];
        front = (front + 1) % capacity;
        size--;
        return data;
    }

    // Peek
    @Override
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return arr[front];
    }

    // Utility methods
    @Override
    public boolean isEmpty() { return size == 0; }
    @Override
    public boolean isFull() { return size == capacity; }
    @Override
    public int size() { return size; }

    @Override
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue (front -> rear): ");
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % capacity;
            System.out.print(arr[idx] + " ");
        }
        System.out.println();
    }
}