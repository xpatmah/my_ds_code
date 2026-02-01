package com.my.ds.code.interview.designpatterns.solid.srp.correct.impl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.ds.code.interview.designpatterns.solid.srp.correct.domain.SrpUser;

//Handles incoming JSON requests that work on User
public class SrpUserController {

    private SrpUserPersistenceService persistenceService = new SrpUserPersistenceService();
    
    //Create a new user
    public String createUser(String userJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SrpUser srpUser = mapper.readValue(userJson, SrpUser.class);

        SrpUserValidator validator = new SrpUserValidator();
        boolean valid = validator.validateUser(srpUser);
        
        if(!valid) {
            return "ERROR";
        }

        persistenceService.saveUser(srpUser);
        
        return "SUCCESS";
    } 

}