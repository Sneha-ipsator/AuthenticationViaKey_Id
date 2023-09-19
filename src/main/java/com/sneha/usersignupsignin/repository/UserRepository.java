package com.sneha.usersignupsignin.repository;

import com.sneha.usersignupsignin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

     public Optional<User> findByEmail(String email);

     Optional<User> findByUserLoginId(String userLoginId);

}
