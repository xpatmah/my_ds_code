package com.my.ds.code.heaps.demo;

import com.my.ds.code.heaps.domain.HeapType;
import com.my.ds.code.heaps.impl.ThreadSafeBinaryHeap;

public class ThreadSafeHeapDemo {
    // Demo
    public static void main(String[] args) throws InterruptedException {
        ThreadSafeBinaryHeap<Integer> heap = new ThreadSafeBinaryHeap<>(HeapType.MIN);

        // simple concurrent producers/consumers demo
        Runnable writer = () -> {
            for (int i = 0; i < 20; i++) {
                heap.insert((int)(Math.random()*100));
                try { Thread.sleep(5); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        };

        Runnable reader = () -> {
            for (int i = 0; i < 10; i++) {
                Integer v = heap.extract();
                // might be null if empty
                System.out.println("Extracted: " + v);
                try { Thread.sleep(20); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        };

        Thread t1 = new Thread(writer);
        Thread t2 = new Thread(writer);
        Thread t3 = new Thread(reader);

        t1.start(); t2.start(); t3.start();
        t1.join(); t2.join(); t3.join();

        System.out.println("Final heap (level-order): " + heap.levelOrder());
    }
}
