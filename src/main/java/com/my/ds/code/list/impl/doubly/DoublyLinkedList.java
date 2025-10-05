package com.my.ds.code.list.impl.doubly;

import com.my.ds.code.list.contract.AbstractLinkedList;
import com.my.ds.code.list.domain.DoublyNode;

public class DoublyLinkedList extends AbstractLinkedList {
    private DoublyNode head;
    private DoublyNode tail;

    public DoublyLinkedList() { head = tail = null; }

    @Override
    public void insertAtBeginning(int data) {
        DoublyNode n = new DoublyNode(data);
        if (head == null) { head = tail = n; return; }
        n.setNext(head);
        head.setPrev(n);
        head = n;
    }

    @Override
    public void insertAtEnd(int data) {
        DoublyNode n = new DoublyNode(data);
        if (tail == null) { head = tail = n; return; }
        tail.setNext(n);
        n.setPrev(tail);
        tail = n;
    }

    @Override
    public void insertAtPosition(int data, int position) {
        if (position < 1) { System.out.println("Position should be >= 1"); return; }
        if (position == 1) { insertAtBeginning(data); return; }
        DoublyNode t = head;
        for (int i = 1; t != null && i < position - 1; i++) t = t.getNext();
        if (t == null) { System.out.println("Position out of range"); return; }
        if (t == tail) { insertAtEnd(data); return; }
        DoublyNode n = new DoublyNode(data);
        DoublyNode next = t.getNext();
        t.setNext(n);
        n.setPrev(t);
        n.setNext(next);
        if (next != null) next.setPrev(n);
    }

    @Override
    public void deleteAtPosition(int position) {
        if (head == null) { System.out.println("List is empty"); return; }
        if (position == 1) {
            head = head.getNext();
            if (head != null) head.setPrev(null);
            else tail = null;
            return;
        }
        DoublyNode t = head;
        for (int i = 1; t != null && i < position; i++) t = t.getNext();
        if (t == null) { System.out.println("Position out of range"); return; }
        DoublyNode p = t.getPrev();
        DoublyNode nx = t.getNext();
        if (p != null) p.setNext(nx);
        if (nx != null) nx.setPrev(p);
        if (t == tail) tail = p;
    }

    @Override
    public boolean search(int key) {
        DoublyNode t = head;
        while (t != null) {
            if (t.getData() == key) return true;
            t = t.getNext();
        }
        return false;
    }

    @Override
    public int size() {
        int count = 0;
        DoublyNode t = head;
        while (t != null) { count++; t = t.getNext(); }
        return count;
    }

    @Override
    public void display() {
        if (head == null) { System.out.println("List is empty"); return; }
        DoublyNode t = head;
        System.out.print("Forward: ");
        while (t != null) {
            System.out.print(t.getData() + " <-> ");
            t = t.getNext();
        }
        System.out.println("null");
    }

    // Extra: display backward to show doubly behaviour
    public void displayBackward() {
        if (tail == null) { System.out.println("List is empty"); return; }
        DoublyNode t = tail;
        System.out.print("Backward: ");
        while (t != null) {
            System.out.print(t.getData() + " <-> ");
            t = t.getPrev();
        }
        System.out.println("null");
    }
}