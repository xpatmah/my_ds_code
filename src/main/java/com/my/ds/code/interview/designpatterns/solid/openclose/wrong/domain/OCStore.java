package com.my.ds.code.interview.designpatterns.solid.openclose.wrong.domain;

import java.util.HashMap;
import java.util.Map;

//Stores data in memory
public class OCStore {

    private static final Map<String, OCUser> STORAGE = new HashMap<>();

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