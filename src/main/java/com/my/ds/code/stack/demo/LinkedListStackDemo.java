package com.my.ds.code.stack.demo;


// Demo
public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        stack.push(100);
        stack.push(200);
        stack.push(300);
        stack.display();

        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        stack.display();
    }
}
