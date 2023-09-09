package com.sneha.usersignupsignin.repository;

import com.sneha.usersignupsignin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

//    User findByUserId(Integer userId);

     User findByUsername(String username);
}
