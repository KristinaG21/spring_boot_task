package com.example.spring_boot_task.controllers;


import com.example.spring_boot_task.exception.UserNotFoundException;
import com.example.spring_boot_task.model.User;
import com.example.spring_boot_task.service.UserService;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class UserControllersTest {


    @InjectMocks
    UserControllers userControllers;

    @Mock
    UserService userService;



    @Test
    void getAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(2, "Kris", "Gejenko", 45));
        Mockito.when(userService.getAllUsers()).thenReturn(userList);
        List<User> allUsers = userService.getAllUsers();
        assertEquals(allUsers, userList);
        Mockito.verify(userService).getAllUsers();
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    public void getById()  {
        final int id = 2;
        Mockito.when(userService.getById(id)).thenReturn(null);
        assertThrows(UserNotFoundException.class,
                ()->{
                    userControllers.getById(2);
                    Mockito.verify(userService).getById(2);
                    Mockito.verifyNoMoreInteractions(userService);
                }
        );


        }


    @Test()
    void getByName()  {
        final String name = "Kris";
        final User user = new User("Kris", "Gejenko", 45);
        Mockito.when(userService.getByName("Kris")).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                ()->{
                    Optional<User> expected = userControllers.getByName("Kris");
                    assertThat(expected).isPresent();
                    Mockito.verify(userService).getByName("Kris");
                    Mockito.verifyNoMoreInteractions(userService);
                }
        );

    }

    @Test
    void getByAge()  {
        final int age = 28;
        final User user = new User("Kris", "Gejenko", 28);
        Mockito.when(userService.getByAge(28)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                ()->{
                    Optional<User> expected = userControllers.getByAge(28);
                    assertThat(expected).isPresent();
                    Mockito.verify(userService).getByAge(28);
                    Mockito.verifyNoMoreInteractions(userService);
                }
        );
    }

//    @Test
//    public void saveUser() {
//        final User user = new User("Kris","Gejenko",28);
//        Mockito.when(userService.saveUser(argumentCaptor.capture())).thenReturn(null);
//        userService.saveUser(user);
//        User value = argumentCaptor.getValue();
//        assertEquals(value, user);
//        assertEquals(value.getName(), "Kris");
//        assertEquals(value.getSurname(), "Gejenko");
//        Mockito.verify(userRepository).save(value);
//        Mockito.verifyNoMoreInteractions(userRepository);
//    @Test
//    void test_exception_custom() {
//        Exception exception = assertThrows(
//                UserNotFoundException.class,
//                () -> getByAge());
//
//        assertTrue(exception.getMessage().contains("User not Found"));
//        Mockito.verify(userService);
//    }


}



