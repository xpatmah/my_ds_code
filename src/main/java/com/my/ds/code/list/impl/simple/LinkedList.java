package com.my.ds.code.list.impl.simple;

import com.my.ds.code.list.contract.AbstractLinkedList;
import com.my.ds.code.list.domain.Node;

public class LinkedList extends AbstractLinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    @Override
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.setNext(head);
        head = newNode;
    }

    @Override
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(newNode);
    }

    @Override
    public void insertAtPosition(int data, int position) {
        if (position < 1) {
            System.out.println("Position should be >= 1");
            return;
        }
        if (position == 1) {
            insertAtBeginning(data);
            return;
        }
        Node newNode = new Node(data);
        Node temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.getNext();
        }
        if (temp == null) {
            System.out.println("Position out of range");
            return;
        }
        newNode.setNext(temp.getNext());
        temp.setNext(newNode);
    }

    @Override
    public void deleteAtPosition(int position) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (position == 1) {
            head = head.getNext();
            return;
        }
        Node temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.getNext();
        }
        if (temp == null || temp.getNext() == null) {
            System.out.println("Position out of range");
            return;
        }
        temp.setNext(temp.getNext().getNext());
    }

    @Override
    public boolean search(int key) {
        Node temp = head;
        while (temp != null) {
            if (temp.getData() == key) return true;
            temp = temp.getNext();
        }
        return false;
    }

    @Override
    public int size() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    @Override
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.getData() + " -> ");
            temp = temp.getNext();
        }
        System.out.println("null");
    }
}
