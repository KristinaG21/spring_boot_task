package com.example.spring_boot_task.repository;



import com.example.spring_boot_task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    User getById(int id);


//    http://localhost:8080/users/?name=Kim

    //    @Query("SELECT a FROM User a WHERE a.name = ?1")
    List<User> getByName(String name);
}
