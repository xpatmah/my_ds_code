package com.my.ds.code.queue;

public class ArrayQueue {
    private int[] arr;
    private int front, rear, size, capacity;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Enqueue
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
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return arr[front];
    }

    // Utility methods
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }
    public int size() { return size; }

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