package com.my.ds.code.queue.impl.circular.arrayBased;

import com.my.ds.code.queue.contract.AbstractCircularQueue;

import java.util.NoSuchElementException;

/* ------------------------
   2) Circular Queue - array backed (wrap-around), fixed capacity
   Uses modulo arithmetic to reuse space.
   ------------------------ */
public class CircularQueueArrayBased extends AbstractCircularQueue {
    private int[] arr;
    private int front;
    private int rear;   // points to next insertion index
    private int capacity;
    private int size;

    public CircularQueueArrayBased(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    // enqueue (offer) - O(1)
    public void enqueue(int x) {
        if (size == capacity) {
            System.out.println("CircularQueue: Overflow");
        }
        arr[rear] = x;
        rear = (rear + 1) % capacity;
        size++;
    }

    // dequeue (poll) - O(1)
    public int dequeue() {
        if (isEmpty()) throw new NoSuchElementException("CircularQueue is empty");
        int val = arr[front];
        front = (front + 1) % capacity;
        size--;
        return val;
    }

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("CircularQueue is empty");
        return arr[front];
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }
    public int size() { return size; }

    public void display() {
        if (isEmpty()) { System.out.println("CircularQueue: empty"); return; }
        System.out.print("CircularQueue [front->rear): ");
        for (int i = 0, idx = front; i < size; i++, idx = (idx + 1) % capacity) {
            System.out.print(arr[idx] + " ");
        }
        System.out.println();
    }
}
