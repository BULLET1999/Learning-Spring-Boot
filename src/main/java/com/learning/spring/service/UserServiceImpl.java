package com.learning.spring.service;

import com.learning.spring.dao.UserRepository;
import com.learning.spring.model.Users;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Users getUserByFirstAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Users createUser(Users user) {
        Optional<Users> getUserByEmail = userRepository.findByEmail(user.getEmail());
        if (getUserByEmail.isPresent()) {
            throw new EntityExistsException("User with email " + user.getEmail() + " already exists!");
        }
        return userRepository.save(user);
    }

    @Override
    public String deleteUserById(long id) {
        Users deleteUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.delete(deleteUser);
        return "User Deleted Successfully";
    }

    @Override
    public String updateUser(Users user) {
        //Get user if exists
        Users existingUser = userRepository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);
        existingUser.setCity(user.getCity());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);
        return "Update Successful";
    }
}
