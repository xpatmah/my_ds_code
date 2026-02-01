package com.my.ds.code.interview.designpatterns.solid.srp.wrong.domain;

import java.util.HashMap;
import java.util.Map;

//Stores data in memory
public class SrpStore {
	
    private static final Map<String, SrpUser> STORAGE = new HashMap<>();
    //Adds user to in memory hash map
    public void store(SrpUser srpUser) {
        synchronized(STORAGE) {
            STORAGE.put(srpUser.getName(), srpUser);
        }
    }
    //Returns used with given id if present in map else null
    public SrpUser getUser(String name) {
        synchronized(STORAGE) {
            return STORAGE.get(name);
        }
    }
}