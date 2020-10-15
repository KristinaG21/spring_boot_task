package com.example.spring_boot_task.repository;



import com.example.spring_boot_task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    User getById(int id);


//    http://localhost:8080/users/?name=Kim

    @Query("select u from User u where u.name = ?1")
    List<User> findByFirstName(String name);

    List<User> findByAgeGreaterThan(int age);
}
