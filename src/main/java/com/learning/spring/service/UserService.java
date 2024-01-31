package com.learning.spring.service;

import com.learning.spring.model.Users;

import java.util.List;

public interface UserService {
    public List<Users> getAllUsers();
    public Users getUserById(long id);
    public Users getUserByFirstAndLastName(String firstName, String lastName);
    public Users createUser(Users user);
    public String deleteUserById(long id);
    public String updateUser(Users user);
}
