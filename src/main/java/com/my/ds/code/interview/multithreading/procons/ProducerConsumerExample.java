package com.my.ds.code.interview.multithreading.procons;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService producer = Executors.newSingleThreadExecutor();

        ExecutorService consumer = Executors.newSingleThreadExecutor();

        Queue<Integer> intQueue = new LinkedList<>();

        producer.submit(new Producer(intQueue,20));
        consumer.submit(new Consumer(intQueue,20));

        consumer.awaitTermination(20, TimeUnit.MINUTES);

    }

}
