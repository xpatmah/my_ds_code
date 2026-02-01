package com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain;


import com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain.Store;
import com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain.User;

import java.util.ArrayList;
import java.util.List;


public class ListStore extends Store {

    private static final List<User> STORAGE = new ArrayList<>();

    @Override
    public void store(User user) {
        synchronized(STORAGE) {
            STORAGE.add(user);
        }
    }

    @Override
    public User getUser(String name) {
        synchronized(STORAGE) {
            return STORAGE.stream().filter(user-> user.getName().equals(name)).findFirst().get();
        }
    }
}
