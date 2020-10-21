package com.example.spring_boot_task.service;

import com.example.spring_boot_task.exception.UserNotFoundException;
import com.example.spring_boot_task.model.User;
import com.example.spring_boot_task.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)

class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Captor
    ArgumentCaptor<User> argumentCaptor;


    @Test
    void getAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(2,"Kris","Gejenko",45));
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<User> allUsers = userService.getAllUsers();
        assertEquals(allUsers, userList);
        Mockito.verify(userRepository).findAll();
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    void getById() {
        final int id = 2;
        final User user = new User(2,"Kris","Gejenko",45);
        Mockito.when(userRepository.getById(id)).thenReturn(user);
        User expected = userService.getById(id);
        assertEquals(user,expected);
        Mockito.verify(userRepository).getById(id);
        Mockito.verifyNoMoreInteractions(userRepository);

    }



    @Test
    void getByName() {
      final String name = "Kris";
      final User user = new User("Kris","Gejenko",45);
        Mockito.when(userRepository.findByFirstName("Kris")).thenReturn(Optional.of(user));
        Optional<User> expected = userService.getByName("Kris");
        assertThat(expected).isPresent();
        Mockito.verify(userRepository).findByFirstName("Kris");
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    void getByAge() {
        final int age = 28;
        final User user = new User("Kris","Gejenko",28);
        Mockito.when(userRepository.findByAgeGreaterThan(28)).thenReturn(Optional.of(user));
        Optional<User> expected = userService.getByAge(28);
        assertThat(expected).isPresent();
        Mockito.verify(userRepository).findByAgeGreaterThan(28);
        Mockito.verifyNoMoreInteractions(userRepository);
    }



    @Test
    void saveUser() {
        final User user = new User("Kris","Gejenko",28);
        Mockito.when(userRepository.save(argumentCaptor.capture())).thenReturn(null);
        userService.saveUser(user);
        User value = argumentCaptor.getValue();
        assertEquals(value, user);
        assertEquals(value.getName(), "Kris");
        assertEquals(value.getSurname(), "Gejenko");
        Mockito.verify(userRepository).save(value);
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    }
