package com.my.ds.code.queue.domain;

// Node class
public class Node {
    private int data;
    private Node next;
    //for Circular interface
    private Node prev;


    public Node(int data) { this.data = data; this.next = null; }

    public int getData() { return data; }
    public Node getNext() { return next; }
    public void setNext(Node next) { this.next = next; }

    public Node getPrev() { return prev; }
    public void setPrev(Node prev) { this.prev = prev; }
}
