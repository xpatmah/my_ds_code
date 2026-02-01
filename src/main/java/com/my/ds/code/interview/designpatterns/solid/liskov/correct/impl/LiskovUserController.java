package com.my.ds.code.interview.designpatterns.solid.liskov.correct.impl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.ds.code.interview.designpatterns.solid.liskov.correct.domain.LiskovUser;


//Handles incoming JSON requests that work on User
public class LiskovUserController {

    private LiskovUserPersistenceService persistenceService = new LiskovUserPersistenceService();
    
    //Create a new user
    public String createUser(String userJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        LiskovUser liskovUser = mapper.readValue(userJson, LiskovUser.class);

        LiskovUserValidator validator = new LiskovUserValidator();
        boolean valid = validator.validateUser(liskovUser);
        
        if(!valid) {
            return "ERROR";
        }

        persistenceService.saveUser(liskovUser);
        
        return "SUCCESS";
    } 

}