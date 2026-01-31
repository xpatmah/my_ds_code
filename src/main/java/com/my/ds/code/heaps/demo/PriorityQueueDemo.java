package com.my.ds.code.heaps.demo;

import com.my.ds.code.heaps.domain.Task;
import com.my.ds.code.heaps.impl.GenericPriorityQueue;

import java.util.Comparator;

public class PriorityQueueDemo {
    // ---------------- Demo ----------------
    public static void main(String[] args) {
        // 1) Integer min-heap (natural order)
        GenericPriorityQueue<Integer> minHeap = new GenericPriorityQueue<>();
        minHeap.offer(30);
        minHeap.offer(10);
        minHeap.offer(20);
        minHeap.offer(5);
        System.out.println("MinHeap peek: " + minHeap.peek()); // 5
        while (!minHeap.isEmpty()) System.out.print(minHeap.poll() + " "); // 5 10 20 30
        System.out.println("\n");

        // 2) Integer max-heap (reverse comparator)
        GenericPriorityQueue<Integer> maxHeap = new GenericPriorityQueue<>(Comparator.reverseOrder());
        maxHeap.offer(30);
        maxHeap.offer(10);
        maxHeap.offer(20);
        maxHeap.offer(5);
        System.out.println("MaxHeap peek: " + maxHeap.peek()); // 30
        while (!maxHeap.isEmpty()) System.out.print(maxHeap.poll() + " "); // 30 20 10 5
        System.out.println("\n");

        Comparator<Task> taskComparator = Comparator.comparingInt(Task::getPriority); // smaller priority first
        GenericPriorityQueue<Task> taskQueue = new GenericPriorityQueue<>(taskComparator);
        taskQueue.offer(new Task("A", 50));
        taskQueue.offer(new Task("B", 10));
        taskQueue.offer(new Task("C", 30));
        System.out.println("Tasks by priority:");
        while (!taskQueue.isEmpty()) System.out.print(taskQueue.poll() + " "); // B(10) C(30) A(50)
        System.out.println();
    }
}
