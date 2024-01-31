package com.learning.spring.dao;

import com.learning.spring.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
    Users findByFirstNameAndLastName(String firstName, String lastName);

    //JPQL queries
    @Query("SELECT u FROM Users u")
    List<Users> USERS_LIST();
    @Query("SELECT u FROM Users u WHERE u.firstName = :firstName")
    Users getUserByFirstName(@Param("firstName") String firstName);

    //Native queries
    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    List<Users> getUsers();
}
