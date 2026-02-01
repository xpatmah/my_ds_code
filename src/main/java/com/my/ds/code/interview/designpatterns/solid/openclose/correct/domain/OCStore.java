package com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain;


//Stores data in memory
public abstract class OCStore {

    public abstract void store(OCUser OCUser);

    public abstract OCUser getUser(String name);
}