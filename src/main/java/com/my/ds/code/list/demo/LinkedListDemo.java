package com.my.ds.code.list.demo;


import com.my.ds.code.list.contract.AbstractLinkedList;
import com.my.ds.code.list.impl.circularDoubly.CircularDoublyLinkedList;
import com.my.ds.code.list.impl.circularSimple.CircularLinkedList;
import com.my.ds.code.list.impl.doubly.DoublyLinkedList;
import com.my.ds.code.list.impl.simple.LinkedList;


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

        // Circular doubly
        System.out.println("\n=== Circular Doubly Linked List ===");
        CircularDoublyLinkedList cdConcrete = new CircularDoublyLinkedList();
        AbstractLinkedList cdList = cdConcrete;
        cdList.insertAtEnd(10);
        cdList.insertAtEnd(20);
        cdList.insertAtEnd(30);
        cdList.insertAtBeginning(5);
        cdList.insertAtPosition(15, 3);
        cdList.display();
        System.out.println("Size: " + cdList.size());
        System.out.println("Search 20? " + cdList.search(20));
        cdList.deleteAtPosition(2);
        cdList.display();
        // display backward is specific to CircularDoublyLinkedList:
        cdConcrete.displayBackward();
    }
}