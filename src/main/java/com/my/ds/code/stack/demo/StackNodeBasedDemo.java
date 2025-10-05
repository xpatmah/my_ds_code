package com.my.ds.code.stack.demo;

import com.my.ds.code.stack.impl.nodeBased.StackNodeBased;

// Demo
public class StackNodeBasedDemo {
    public static void main(String[] args) {

        StackNodeBased stack = new StackNodeBased();
        stack.push(100);
        stack.push(200);
        stack.push(300);
        stack.display();

        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        stack.display();
    }
}
