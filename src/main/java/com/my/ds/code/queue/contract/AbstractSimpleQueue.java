package com.my.ds.code.queue.contract;

public abstract class AbstractSimpleQueue {
    // Enqueue
    public abstract void enqueue(int data);

    // Dequeue
    public abstract int dequeue();

    // Peek
    public abstract int peek();

    // Utility methods
    public abstract boolean isEmpty();

    public abstract boolean isFull();

    public abstract int size();

    public abstract void display();
}
