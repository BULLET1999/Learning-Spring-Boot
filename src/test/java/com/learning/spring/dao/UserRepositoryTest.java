package com.learning.spring.dao;

import com.learning.spring.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUserTest() {
        Users users = new Users(
                1L,
                "Nikhil",
                "Jangra",
                "nikhiljangrakumar@gmail.com",
                "Sonipat",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));
        System.out.println(users);
        userRepository.save(users);
        assertThat(users.getId()).isGreaterThan(0);
    }

    @Test
    void findByFirstNameAndLastNameTest() {
        Users byFirstNameAndLastName = userRepository.findByFirstNameAndLastName("Nikhil", "Jangra");
        assertNotNull(byFirstNameAndLastName);
    }

    @Test
    void USERS_LIST() {
        System.out.println(userRepository.USERS_LIST());
        assertNotNull(userRepository.USERS_LIST());
    }

    @Test
    void getUserByFirstName() {
    }

    @Test
    void getUsers() {
    }
}