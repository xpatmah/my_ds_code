package com.my.ds.code.stack.impl.array;

// Stack implementation using Array
public class ArrayStack {
    private int[] arr;
    private int top;
    private int capacity;

    // Constructor
    public ArrayStack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    // Push
    public void push(int data) {
        if (isFull()) {
            System.out.println("Stack Overflow!");
            return;
        }
        arr[++top] = data;
    }

    // Pop
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        return arr[top--];
    }

    // Peek
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return arr[top];
    }

    // Utility
    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return top == capacity - 1; }
    public int size() { return top + 1; }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack (top -> bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

