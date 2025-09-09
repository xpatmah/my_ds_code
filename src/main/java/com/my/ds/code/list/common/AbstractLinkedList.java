package com.my.ds.code.list.common;

public abstract class AbstractLinkedList {
    public abstract void insertAtBeginning(int data);
    public abstract void insertAtEnd(int data);
    public abstract void insertAtPosition(int data, int position);
    public abstract void deleteAtPosition(int position);
    public abstract boolean search(int key);
    public abstract int size();
    public abstract void display();
}
