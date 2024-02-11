package com.rest.webservices.restfulwebservices.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {
    private UserDAOService service;
    public UserResource(UserDAOService service){
        this.service=service;
    }
//    GET users
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }
    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable int id){
        return service.findOne(id);
    }


}
