package com.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOService {
    private static List<User> users=new ArrayList<>();
    static{
        users.add(new User(1,"Adam", LocalDate.now().minusYears(25)));
        users.add(new User(2,"Ava", LocalDate.now().minusYears(20)));
        users.add(new User(3,"Sheldon", LocalDate.now().minusYears(30)));
    }
    public List<User> findAll(){
        return users;
    }

//    public User save(User user)
    public User findOne(int id){
        return users.stream().filter(user->user.getId()==id).findFirst().orElse(null);
    }


}
