package com.my.ds.code.interview.multithreading.procons;

import java.util.Queue;

public class Producer implements Runnable {

    private final Queue<Integer> list;
    private final Integer CAPACITY;

    public Producer(Queue<Integer> list, int capacity){
        this.list = list;
        this.CAPACITY = capacity;
    }

    public void run(){
        int i=0;
        while(true) {
            synchronized (list) {
                if (list.size() < this.CAPACITY) {
                    System.out.println("Produced Element " + list.offer(i++) + " , "+i);
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


}
