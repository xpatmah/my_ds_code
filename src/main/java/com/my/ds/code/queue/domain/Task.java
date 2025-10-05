package com.my.ds.code.queue.domain;

// 3) Custom object with comparator
public class Task {
    final String name;
    final int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return name + "(" + priority + ")";
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }
}