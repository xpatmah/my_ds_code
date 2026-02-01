package com.my.ds.code.interview.designpatterns.solid.liskov.wrong.impl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.ds.code.interview.designpatterns.solid.liskov.wrong.domain.User;


//Handles incoming JSON requests that work on User
public class UserController {

    private UserPersistenceService persistenceService = new UserPersistenceService();
    
    //Create a new user
    public String createUser(String userJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userJson, User.class);

        UserValidator validator = new UserValidator();
        boolean valid = validator.validateUser(user);
        
        if(!valid) {
            return "ERROR";
        }

        persistenceService.saveUser(user);
        
        return "SUCCESS";
    } 

}