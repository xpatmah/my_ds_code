package com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain;


import java.util.ArrayList;
import java.util.List;


public class OCListOCStore extends OCStore {

    private static final List<OCUser> STORAGE = new ArrayList<>();

    @Override
    public void store(OCUser OCUser) {
        synchronized(STORAGE) {
            STORAGE.add(OCUser);
        }
    }

    public OCUser getUser(String name) {
        synchronized(STORAGE) {
            return STORAGE.stream().filter(user-> user.getName().equals(name)).findFirst().get();
        }
    }
}
