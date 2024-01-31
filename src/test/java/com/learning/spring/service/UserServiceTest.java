package com.learning.spring.service;

import com.learning.spring.dao.UserRepository;
import com.learning.spring.model.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private Users users;

    @BeforeEach
    void setUp() {
        users = new Users(1L,
                "Nikhil",
                "Jangra",
                "nikhiljangrakumar@gmail.com",
                "Sonipat",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));
    }

    @DisplayName(value = "JUnit test to get user using id")
    @Test
    public void getUserByIdTest() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(users));
        Users result = userService.getUserById(userId);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Nikhil", result.getFirstName());
        Assertions.assertEquals("Jangra", result.getLastName());
        Assertions.assertEquals("nikhiljangrakumar@gmail.com", result.getEmail());
        Assertions.assertEquals("Sonipat", result.getCity());
        System.out.println(result);
    }

    @DisplayName(value = "JUnit test for createUser method")
    @Test
    public void givenUserObject_whenSaveUser_thenReturnUserObject() {

        given(userRepository.findByEmail(users.getEmail())).willReturn(Optional.empty());
        given(userRepository.save(users)).willReturn(users);
        System.out.println(userRepository);
        System.out.println(userService);
        Users savedUser = userService.createUser(users);
        System.out.println(savedUser);
        assertThat(savedUser).isNotNull();
    }

}

