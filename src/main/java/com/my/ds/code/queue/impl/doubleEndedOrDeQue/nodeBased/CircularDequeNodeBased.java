package com.my.ds.code.queue.impl.doubleEndedOrDeQue.nodeBased;

import com.my.ds.code.queue.contract.AbstractCircularDeque;
import com.my.ds.code.queue.domain.Node;

/**
 * Linked List based Circular Deque Implementation.
 * Doubly Linked structure allows O(1) add/remove at both ends.
 */
public class CircularDequeNodeBased extends AbstractCircularDeque {

    private Node front;
    private Node rear;
    private int size;
    private int capacity; // optional, can be unbounded if capacity=0

    // Constructors
    public CircularDequeNodeBased() { this(0); } // unbounded
    public CircularDequeNodeBased(int capacity) {
        this.capacity = capacity;
        front = rear = null;
        size = 0;
    }

    @Override
    public boolean isFull() {
        if (capacity == 0) return false; // unbounded
        return size == capacity;
    }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void enqueue(int data) { addRear(data); }

    @Override
    public void addFront(int data) {
        if (isFull()) {
            System.out.println("Deque Overflow!");
            return;
        }
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
            front.setNext(front);
            front.setPrev(front);
        } else {
            newNode.setNext(front);
            newNode.setPrev(rear);
            front.setPrev(newNode);
            rear.setNext(newNode);
            front = newNode;
        }
        size++;
    }

    private void addRear(int data) {
        if (isFull()) {
            System.out.println("Deque Overflow!");
            return;
        }
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
            newNode.setNext(newNode);
            newNode.setPrev(newNode);
        } else {
            newNode.setPrev(rear);
            newNode.setNext(front);
            rear.setNext(newNode);
            front.setPrev(newNode);
            rear = newNode;
        }
        size++;
    }

    @Override
    public int dequeue() { return removeFront(); }

    private int removeFront() {
        if (isEmpty()) {
            System.out.println("Deque Underflow!");
            return -1;
        }
        int val = front.getData();
        if (size == 1) {
            front = rear = null;
        } else {
            front = front.getNext();
            front.setPrev(rear);
            rear.setNext(front);
        }
        size--;
        return val;
    }

    @Override
    public int removeRear() {
        if (isEmpty()) {
            System.out.println("Deque Underflow!");
            return -1;
        }
        int val = rear.getData();
        if (size == 1) {
            front = rear = null;
        } else {
            rear = rear.getPrev();
            rear.setNext(front);
            front.setPrev(rear);
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
            System.out.println("Deque is empty");
            return;
        }
        System.out.print("CircularDequeLinkedList (front -> rear): ");
        Node temp = front;
        do {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        } while (temp != front);
        System.out.println();
    }
}
