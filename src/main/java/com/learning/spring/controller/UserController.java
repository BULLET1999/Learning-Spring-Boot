package com.learning.spring.controller;

import com.learning.spring.dao.UserRepository;
import com.learning.spring.model.Users;
import com.learning.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public ResponseEntity<Object> keepAlive() {
        int a = 25;
        int b = 1;
        return new ResponseEntity<>("The Sum of two number is: " + (a / b), HttpStatus.OK);
    }

    //Get all the users
    @RequestMapping(value = "/get-users", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllUsers() {
        List<Users> users = userRepository.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //Get user by id
    @RequestMapping(value = "/get-user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(
            @PathVariable(name = "id") long id
    ) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    //Get user by firstName
    @RequestMapping(value = "/get-user-by-first-name", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserByFirstName(
            @RequestParam(name = "firstName") String firstName
    ) {
        return new ResponseEntity<>(userRepository.getUserByFirstName(firstName), HttpStatus.OK);
    }

    //Get user by firstName and lastName
    @RequestMapping(value = "/get-user", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserByFirstAndLastName(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName
    ) {
        return new ResponseEntity<>(userService.getUserByFirstAndLastName(firstName, lastName), HttpStatus.OK);
    }

    //Create new user
    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(
            @Valid @RequestBody Users user
    ) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    //Update existing user
    @RequestMapping(value = "/update-user", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(
            @Valid @RequestBody Users user
    ) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    //Delete the existing user
    @RequestMapping(value = "/delete-user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(
            @PathVariable(name = "id") long id
    ) {
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.NO_CONTENT);
    }
}
