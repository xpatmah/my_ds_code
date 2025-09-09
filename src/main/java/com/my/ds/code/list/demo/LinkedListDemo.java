package com.my.ds.code.list.demo;

import com.my.ds.code.list.circular.CircularLinkedList;
import com.my.ds.code.list.common.AbstractLinkedList;
import com.my.ds.code.list.simple.DoublyLinkedList;
import com.my.ds.code.list.simple.LinkedList;


public class LinkedListDemo {
    public static void main(String[] args) {
        System.out.println("=== Singly Linked List ===");
        AbstractLinkedList sList = new LinkedList();
        sList.insertAtEnd(10);
        sList.insertAtEnd(20);
        sList.insertAtBeginning(5);
        sList.insertAtPosition(15, 3);
        sList.display();
        System.out.println("Size: " + sList.size());
        System.out.println("Search 15? " + sList.search(15));
        sList.deleteAtPosition(2);
        sList.display();

        System.out.println("\n=== Doubly Linked List ===");
        DoublyLinkedList dList = new DoublyLinkedList();
        AbstractLinkedList dRef = dList; // polymorphic reference possible
        dRef.insertAtEnd(1);
        dRef.insertAtEnd(2);
        dRef.insertAtEnd(3);
        dRef.insertAtBeginning(0);
        dRef.insertAtPosition(5, 3);
        dRef.display();
        System.out.println("Size: " + dRef.size());
        System.out.println("Search 2? " + dRef.search(2));
        dRef.deleteAtPosition(3);
        dRef.display();
        // show backward traversal specific to doubly list:
        dList.displayBackward();

        System.out.println("\n=== Circular Linked List ===");
        AbstractLinkedList cList = new CircularLinkedList();
        cList.insertAtEnd(100);
        cList.insertAtEnd(200);
        cList.insertAtEnd(300);
        cList.insertAtBeginning(50);
        cList.display();
        System.out.println("Size: " + cList.size());
        System.out.println("Search 200? " + cList.search(200));
        cList.deleteAtPosition(2);
        cList.display();
    }
}