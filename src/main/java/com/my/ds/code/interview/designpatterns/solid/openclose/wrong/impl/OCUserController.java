package com.my.ds.code.interview.designpatterns.solid.openclose.wrong.impl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.ds.code.interview.designpatterns.solid.openclose.wrong.domain.OCUser;

//Handles incoming JSON requests that work on User
public class OCUserController {

    private OCUserPersistenceService persistenceService = new OCUserPersistenceService();
    
    //Create a new user
    public String createUser(String userJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        OCUser ocUser = mapper.readValue(userJson, OCUser.class);

        OCUserValidator validator = new OCUserValidator();
        boolean valid = validator.validateUser(ocUser);
        
        if(!valid) {
            return "ERROR";
        }

        persistenceService.saveUser(ocUser);
        
        return "SUCCESS";
    } 

}