package com.sneha.usersignupsignin.controller;

import com.sneha.usersignupsignin.entity.User;
import com.sneha.usersignupsignin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * This class defines the REST endpoints for user-related operations.
 */
@RestController
//@PreAuthorize("hasRole('USER')")
@RequestMapping("/user")
public class HomeController {



    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Get a list of users.
     *
     * @return ResponseEntity containing a list of users or an error response.
     */

    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<List<User>> getUsers() {
        try{
            List<User> users = userServiceImpl.getUsers();
            System.out.println(users);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (UsernameNotFoundException ne){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Get the currently authenticated user's username.
     *
     * @param userDetails The Principal object containing user details.
     * @return ResponseEntity containing the username or an error response.
     */
    @GetMapping("/current-user")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<String> getCurrentUser(Principal userDetails) {
        // Principal will contain the authenticated user's username (ID);
        System.out.println(userDetails);
        if (userDetails != null) {
            String username = userDetails.getName();

            return new ResponseEntity<>(username, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
