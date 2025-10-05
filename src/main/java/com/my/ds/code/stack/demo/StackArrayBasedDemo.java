package com.my.ds.code.stack.demo;

import com.my.ds.code.stack.impl.arrayBased.StackArrayBased;

// Demo
public class StackArrayBasedDemo {

    public static void main(String[] args) {

        StackArrayBased stack = new StackArrayBased(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();

        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        stack.display();

    }
}
