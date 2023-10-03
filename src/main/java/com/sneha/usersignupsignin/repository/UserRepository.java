package com.sneha.usersignupsignin.repository;

import com.sneha.usersignupsignin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing user data in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

     /**
      * Find a user by their email address.
      *
      * @param email The email address of the user to search for.
      * @return An optional containing the user with the specified email address, or an empty optional if not found.
      */
     public Optional<User> findByEmail(String email);

     /**
      * Find a user by their login ID.
      *
      * @param userLoginId The login ID of the user to search for.
      * @return An optional containing the user with the specified login ID, or an empty optional if not found.
      */
     Optional<User> findByUserLoginId(String userLoginId);

}
