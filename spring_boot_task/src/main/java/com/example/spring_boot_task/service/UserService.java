package com.example.spring_boot_task.service;



import com.example.spring_boot_task.controllers.UserControllers;
import com.example.spring_boot_task.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import com.example.spring_boot_task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

   @Autowired
     UserRepository userRepository;


    @Scheduled(cron = "${cron.expression}")
//    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        System.out.println("getAll service call in " + new Date().toString());
        simulateSlowService();
        return userRepository.findAll();

    }

    @Cacheable(value = "users", key="#id")
    public User getById(int id) {
        log.info("getting user by id: {}", id);
        return userRepository.getById(id);
    }

    @Cacheable(value = "users", key="#name")
    public Optional<User> getByName(String name) {
        log.info("getting user by name: {}", name);
       return userRepository.findByFirstName(name);


    }

    @Cacheable(value = "users", key="#age")
    public Optional<User> getByAge(int age) {
        log.info("getting user by age: {}", age);
        return userRepository.findByAgeGreaterThan(age);
    }

    public User saveUser(User user) {
        log.info("save user: {}", user);
        return userRepository.save(user);
    }

    private void simulateSlowService() {
        try {

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }


}

