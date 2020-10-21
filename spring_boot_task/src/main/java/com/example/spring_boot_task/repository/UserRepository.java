package com.example.spring_boot_task.repository;

import com.example.spring_boot_task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    User getById(int id);

    @Query("select u from User u where u.name = ?1")
    Optional<User> findByFirstName(String name);

    Optional<User> findByAgeGreaterThan(int age);


}
