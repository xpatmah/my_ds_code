package com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain;


import com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain.User;

//Stores data in memory
public abstract class Store {

    public abstract void store(User user);

    public abstract User getUser(String name);

}