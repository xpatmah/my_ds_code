package com.my.ds.code.interview.designpatterns.solid.liskov.correct.domain;


import java.util.HashMap;

import java.util.Map;

public class LiskovDefaultOCStore extends LiskovStore {

    private static final Map<String, LiskovUser> STORAGE = new HashMap<>();

    @Override
    public void store(LiskovUser OCUser) {
        synchronized(STORAGE) {
            STORAGE.put(OCUser.getName(), OCUser);
        }
    }

    public LiskovUser getUser(String name) {
        synchronized(STORAGE) {
            return STORAGE.get(name);
        }
    }
}
