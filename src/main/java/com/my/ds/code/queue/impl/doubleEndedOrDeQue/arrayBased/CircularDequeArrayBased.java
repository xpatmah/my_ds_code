package com.my.ds.code.queue.impl.doubleEndedOrDeQue.arrayBased;

import com.my.ds.code.queue.contract.AbstractCircularDeque;

public class CircularDequeArrayBased extends AbstractCircularDeque {
    private int[] arr;
    private int front, rear, size, capacity;

    public CircularDequeArrayBased(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    @Override
    public boolean isFull() { return size == capacity; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void enqueue(int data) { addRear(data); }

    private void addRear(int data) {
        if (isFull()) { System.out.println("Deque Overflow!"); return; }
        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }
        arr[rear] = data;
        size++;
    }

    @Override
    public void addFront(int data) {
        if (isFull()) { System.out.println("Deque Overflow!"); return; }
        if (isEmpty()) {
            front = rear = 0;
        } else {
            front = (front - 1 + capacity) % capacity;
        }
        arr[front] = data;
        size++;
    }

    @Override
    public int dequeue() { return removeFront(); }

    private int removeFront() {
        if (isEmpty()) { System.out.println("Deque Underflow!"); return -1; }
        int val = arr[front];
        if (front == rear) { front = rear = -1; }
        else { front = (front + 1) % capacity; }
        size--;
        return val;
    }

    @Override
    public int removeRear() {
        if (isEmpty()) { System.out.println("Deque Underflow!"); return -1; }
        int val = arr[rear];
        if (front == rear) { front = rear = -1; }
        else { rear = (rear - 1 + capacity) % capacity; }
        size--;
        return val;
    }

    @Override
    public int peek() {
        if (isEmpty()) return -1;
        return arr[front];
    }

    @Override
    public int size() { return size; }

    @Override
    public void display() {
        if (isEmpty()) { System.out.println("Deque is empty"); return; }
        System.out.print("CircularDequeArray (front -> rear): ");
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % capacity;
            System.out.print(arr[idx] + " ");
        }
        System.out.println();
    }
}
