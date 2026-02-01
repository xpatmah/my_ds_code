package com.my.ds.code.interview.designpatterns.solid.liskov.correct.domain;


import com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain.OCStore;
import com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain.OCUser;

import java.util.ArrayList;
import java.util.List;


public class LiskovListOCStore extends LiskovStore {

    private static final List<LiskovUser> STORAGE = new ArrayList<>();

    @Override
    public void store(LiskovUser liskovUser) {
        synchronized(STORAGE) {
            STORAGE.add(liskovUser);
        }
    }

    public LiskovUser getUser(String name) {
        synchronized(STORAGE) {
            return STORAGE.stream().filter(user-> user.getName().equals(name)).findFirst().get();
        }
    }
}
