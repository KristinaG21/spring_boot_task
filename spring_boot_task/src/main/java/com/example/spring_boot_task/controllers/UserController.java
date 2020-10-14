package com.example.spring_boot_task.controllers;



import com.example.spring_boot_task.model.User;
import com.example.spring_boot_task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/users/{id}")
    public User getById(@PathVariable int id){
        return userService.getById(id);
    }

    @GetMapping("/users/name/{name}")
    public List<User> getByName(@PathVariable String name){
        return userService.getByName(name);
    }
    @PostMapping("/users")
    public void saveUser(@Valid @RequestBody User user){
        userService.saveUser(user);

    }
}
