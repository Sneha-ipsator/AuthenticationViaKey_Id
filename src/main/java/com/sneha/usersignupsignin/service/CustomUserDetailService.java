package com.sneha.usersignupsignin.service;

import com.sneha.usersignupsignin.entity.User;
import com.sneha.usersignupsignin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of the Spring Security UserDetailsService.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Load a user by their username (user login ID).
     *
     * @param username The username (user login ID) of the user to load.
     * @return UserDetails representing the user.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserLoginId(username).orElseThrow(()->new RuntimeException("User not found !!"));
        return user;
    }
}
