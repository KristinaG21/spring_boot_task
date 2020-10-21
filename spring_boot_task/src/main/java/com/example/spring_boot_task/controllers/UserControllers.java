package com.example.spring_boot_task.controllers;



import com.example.spring_boot_task.exception.UserNotFoundException;
import com.example.spring_boot_task.model.User;
import com.example.spring_boot_task.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class UserControllers {

    private static final Logger log = LoggerFactory.getLogger(UserControllers.class);

    @Autowired
    UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getById(@PathVariable int id){
        User byId = userService.getById(id);
        if(byId==null)
            throw new UserNotFoundException("id:" +id);
        return byId;
    }

    @GetMapping("/users/name/{name}")
    public Optional<User> getByName(@PathVariable String name){
        Optional<User> byName = userService.getByName(name);
        if(!byName.isPresent())
            throw new UserNotFoundException("name" +name);
        return byName;


    }
    @GetMapping("/users/age/{age}")
    public Optional<User> getByAge(@PathVariable int age){
        Optional<User> byAge = userService.getByAge(age);
        if(!byAge.isPresent())
            throw new UserNotFoundException("age" +age);
        return byAge;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){

       User userSaved= userService.saveUser(user);
       if(userSaved==null)
           throw new UserNotFoundException("User not Found");
        log.error("user is null!!!");
      URI location= ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(userSaved.getId()).toUri();
        log.info("User is added");
      return ResponseEntity.created(location).build();




    }
}
