package com.my.ds.code.stack.impl.list;

import com.my.ds.code.list.common.Node;

// Stack implementation using Linked List
class LinkedListStack {
    private Node top;
    private int size;

    public LinkedListStack() {
        this.top = null;
        this.size = 0;
    }

    // Push
    public void push(int data) {
        Node newNode = new Node(data);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    // Pop
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        int popped = top.getData();
        top = top.getNext();
        size--;
        return popped;
    }

    // Peek
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return top.getData();
    }

    // Utility
    public boolean isEmpty() { return top == null; }
    public int size() { return size; }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack (top -> bottom): ");
        Node temp = top;
        while (temp != null) {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }
}

