package com.my.ds.code.interview.designpatterns.solid.liskov.correct.domain;


import com.my.ds.code.interview.designpatterns.solid.openclose.correct.domain.OCUser;

//Stores data in memory
public abstract class LiskovStore {

    public abstract void store(LiskovUser liskovuser);

}