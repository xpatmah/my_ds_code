package com.my.ds.code.list.common;

public class Node {
    private int data;
    private Node next;

    // Constructor
    public Node(int data) {
        this.data = data;
        this.next = null;
    }

    // Getter and Setter for data
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    // Getter and Setter for next
    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}