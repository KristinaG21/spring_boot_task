package com.example.spring_boot_task.service;



import com.example.spring_boot_task.model.User;

import com.example.spring_boot_task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

   @Autowired
     UserRepository userRepository;



    public List<User> getAllUsers() {
        return userRepository.findAll();

    }
    @Cacheable(value = "users")
    public User getById(int id) {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
        return userRepository.getById(id);
    }


    public Optional<User> getByName(String name) {
       return userRepository.findByFirstName(name);


    }


    public Optional<User> getByAge(int age) {
        return userRepository.findByAgeGreaterThan(age);
    }

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }


}

