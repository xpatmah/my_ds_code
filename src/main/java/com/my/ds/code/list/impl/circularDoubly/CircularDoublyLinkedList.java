package com.my.ds.code.list.impl.circularDoubly;

import com.my.ds.code.list.contract.AbstractLinkedList;
import com.my.ds.code.list.domain.DoublyNode;

public class CircularDoublyLinkedList extends AbstractLinkedList {
    private DoublyNode head;
    private int size;

    public CircularDoublyLinkedList() { head = null; size = 0; }

    @Override
    public void insertAtBeginning(int data) {
        DoublyNode n = new DoublyNode(data);
        if (head == null) {
            head = n;
            head.setNext(head);
            head.setPrev(head);
        } else {
            DoublyNode tail = head.getPrev();
            n.setNext(head);
            n.setPrev(tail);
            head.setPrev(n);
            tail.setNext(n);
            head = n;
        }
        size++;
    }

    @Override
    public void insertAtEnd(int data) {
        DoublyNode n = new DoublyNode(data);
        if (head == null) {
            head = n;
            head.setNext(head);
            head.setPrev(head);
        } else {
            DoublyNode tail = head.getPrev();
            tail.setNext(n);
            n.setPrev(tail);
            n.setNext(head);
            head.setPrev(n);
        }
        size++;
    }

    @Override
    public void insertAtPosition(int data, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Position out of range");
            return;
        }
        if (position == 1) { insertAtBeginning(data); return; }
        if (position == size + 1) { insertAtEnd(data); return; }

        DoublyNode t = head;
        for (int i = 1; i < position - 1; i++) t = t.getNext();
        DoublyNode n = new DoublyNode(data);
        DoublyNode nxt = t.getNext();
        n.setNext(nxt);
        n.setPrev(t);
        t.setNext(n);
        nxt.setPrev(n);
        size++;
    }

    @Override
    public void deleteAtPosition(int position) {
        if (head == null || position < 1 || position > size) {
            System.out.println("Position out of range or list empty");
            return;
        }
        if (size == 1) { head = null; size = 0; return; }
        if (position == 1) {
            DoublyNode tail = head.getPrev();
            head = head.getNext();
            head.setPrev(tail);
            tail.setNext(head);
            size--;
            return;
        }
        DoublyNode t = head;
        for (int i = 1; i < position; i++) t = t.getNext();
        DoublyNode prev = t.getPrev();
        DoublyNode nxt = t.getNext();
        prev.setNext(nxt);
        nxt.setPrev(prev);
        if (t == head) head = nxt; // safety
        size--;
    }

    @Override
    public boolean search(int key) {
        if (head == null) return false;
        DoublyNode t = head;
        do {
            if (t.getData() == key) return true;
            t = t.getNext();
        } while (t != head);
        return false;
    }

    @Override
    public int size() { return size; }

    @Override
    public void display() {
        if (head == null) { System.out.println("List is empty"); return; }
        DoublyNode t = head;
        System.out.print("CDLL Forward: ");
        do {
            System.out.print(t.getData() + " <-> ");
            t = t.getNext();
        } while (t != head);
        System.out.println("(back to head)");
    }

    public void displayBackward() {
        if (head == null) { System.out.println("List is empty"); return; }
        DoublyNode tail = head.getPrev();
        DoublyNode t = tail;
        System.out.print("CDLL Backward: ");
        do {
            System.out.print(t.getData() + " <-> ");
            t = t.getPrev();
        } while (t != tail);
        System.out.println("(back to tail)");
    }
}