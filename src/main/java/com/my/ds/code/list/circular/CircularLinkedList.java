package com.my.ds.code.list.circular;

import com.my.ds.code.list.common.AbstractLinkedList;
import com.my.ds.code.list.common.Node;

public class CircularLinkedList extends AbstractLinkedList {
    // head points to first node; tail.next == head when non-empty
    private Node head;
    private Node tail;

    public CircularLinkedList() { head = tail = null; }

    @Override
    public void insertAtBeginning(int data) {
        Node n = new Node(data);
        if (head == null) {
            head = tail = n;
            tail.setNext(head);
            return;
        }
        n.setNext(head);
        head = n;
        tail.setNext(head);
    }

    @Override
    public void insertAtEnd(int data) {
        Node n = new Node(data);
        if (head == null) {
            head = tail = n;
            tail.setNext(head);
            return;
        }
        tail.setNext(n);
        tail = n;
        tail.setNext(head);
    }

    @Override
    public void insertAtPosition(int data, int position) {
        if (position < 1) { System.out.println("Position should be >= 1"); return; }
        if (position == 1) { insertAtBeginning(data); return; }
        Node t = head;
        for (int i = 1; t != null && i < position - 1; i++) {
            t = t.getNext();
            if (t == head) break; // avoid infinite loop if position > size+1
        }
        if (t == null || t == head && position > size()+1) { System.out.println("Position out of range"); return; }
        Node n = new Node(data);
        n.setNext(t.getNext());
        t.setNext(n);
        if (t == tail) tail = n;
    }

    @Override
    public void deleteAtPosition(int position) {
        if (head == null) { System.out.println("List is empty"); return; }
        if (position == 1) {
            if (head == tail) { head = tail = null; return; } // single node
            head = head.getNext();
            tail.setNext(head);
            return;
        }
        Node t = head;
        for (int i = 1; t != null && i < position - 1; i++) {
            t = t.getNext();
            if (t == head) break;
        }
        if (t == null || t.getNext() == null || t.getNext() == head) { System.out.println("Position out of range"); return; }
        Node toDelete = t.getNext();
        t.setNext(toDelete.getNext());
        if (toDelete == tail) tail = t;
    }

    @Override
    public boolean search(int key) {
        if (head == null) return false;
        Node t = head;
        do {
            if (t.getData() == key) return true;
            t = t.getNext();
        } while (t != null && t != head);
        return false;
    }

    @Override
    public int size() {
        if (head == null) return 0;
        int count = 0;
        Node t = head;
        do {
            count++;
            t = t.getNext();
        } while (t != null && t != head);
        return count;
    }

    @Override
    public void display() {
        if (head == null) { System.out.println("List is empty"); return; }
        Node t = head;
        System.out.print("Circular: ");
        do {
            System.out.print(t.getData() + " -> ");
            t = t.getNext();
        } while (t != null && t != head);
        System.out.println("(back to head)");
    }
}
