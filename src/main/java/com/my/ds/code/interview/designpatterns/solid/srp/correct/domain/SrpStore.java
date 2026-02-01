package com.my.ds.code.interview.designpatterns.solid.srp.correct.domain;

import java.util.HashMap;
import java.util.Map;

//Stores data in memory
public class SrpStore {

    private static final Map<String, SrpUser> STORAGE = new HashMap<>();

    public void store(SrpUser srpUser) {
        synchronized(STORAGE) {
            STORAGE.put(srpUser.getName(), srpUser);
        }
    }

    public SrpUser getUser(String name) {
        synchronized(STORAGE) {
            return STORAGE.get(name);
        }
    }
}