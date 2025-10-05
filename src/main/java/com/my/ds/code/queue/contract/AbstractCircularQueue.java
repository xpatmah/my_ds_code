package com.my.ds.code.queue.contract;

// Common interface for all Circular Queue implementations
public abstract class AbstractCircularQueue {
    public abstract void enqueue(int data);
    public abstract int dequeue();
    public abstract int peek();
    public abstract boolean isEmpty();
    public abstract boolean isFull(); // for linked list impl, always false
    public abstract int size();
    public abstract void display();
}