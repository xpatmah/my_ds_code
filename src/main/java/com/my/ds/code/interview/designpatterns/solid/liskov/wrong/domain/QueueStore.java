package com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain;


import java.util.LinkedList;
import java.util.Queue;


public class QueueStore extends Store {

    private static final Queue<User> STORAGE = new LinkedList<>();

    @Override
    public void store(User user) {
        synchronized(STORAGE) {
           throw new RuntimeException();
        }
    }

    @Override
    public User getUser(String name) {
        synchronized(STORAGE) {
            return STORAGE.stream().filter(user-> user.getName().equals(name)).findFirst().get();
        }
    }
}
