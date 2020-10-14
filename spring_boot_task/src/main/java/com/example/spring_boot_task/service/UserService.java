package com.example.spring_boot_task.service;



import com.example.spring_boot_task.model.User;
import com.example.spring_boot_task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public User getById(int id) {
        return userRepository.getById(id);
    }
    public List<User> getByName(String name) {
        return userRepository.getByName(name);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
