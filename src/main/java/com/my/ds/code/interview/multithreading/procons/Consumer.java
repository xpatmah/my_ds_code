package com.my.ds.code.interview.multithreading.procons;

import java.util.List;
import java.util.Queue;

public class Consumer implements Runnable{

    private final Queue<Integer> list;

    private final int CAPACITY ;

    public Consumer(Queue<Integer> list , int capacity){
        this.list = list;
        this.CAPACITY = capacity;
    }

    public void run(){
        while(true){
            synchronized(list){
                 if(!list.isEmpty()){
                     System.out.println("Consumed Element "+list.poll());
                 }else{
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

