package com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain;


import java.util.HashMap;

import com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain.Store;
import com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain.User;
import java.util.Map;

public class DefaultStore extends Store {

    private static final Map<String, User> STORAGE = new HashMap<>();

    @Override
    public void store(User user) {
        synchronized(STORAGE) {
            STORAGE.put(user.getName(), user);
        }
    }

    @Override
    public User getUser(String name) {
        synchronized(STORAGE) {
            return STORAGE.get(name);
        }
    }
}
