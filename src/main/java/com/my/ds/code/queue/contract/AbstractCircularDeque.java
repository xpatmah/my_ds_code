package com.my.ds.code.queue.contract;

// Extended interface for Deque
public abstract class AbstractCircularDeque extends AbstractCircularQueue {
    public abstract void addFront(int data);
    public abstract int removeRear();
}