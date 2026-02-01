package com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain;


import java.util.HashMap;
import java.util.Map;

public class OCDefaultOCStore extends OCStore {

    private static final Map<String, OCUser> STORAGE = new HashMap<>();

    @Override
    public void store(OCUser OCUser) {
        synchronized(STORAGE) {
            STORAGE.put(OCUser.getName(), OCUser);
        }
    }

    public OCUser getUser(String name) {
        synchronized(STORAGE) {
            return STORAGE.get(name);
        }
    }
}
