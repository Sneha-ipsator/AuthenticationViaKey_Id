package com.sneha.usersignupsignin.controller;

import com.sneha.usersignupsignin.entity.User;
import com.sneha.usersignupsignin.payload.ApiResponse;
import com.sneha.usersignupsignin.payload.Error;
import com.sneha.usersignupsignin.payload.ServiceResponse;
import com.sneha.usersignupsignin.record.RegisterUserRecord;
import com.sneha.usersignupsignin.repository.UserRepository;
import com.sneha.usersignupsignin.security.JwtHelper;
import com.sneha.usersignupsignin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")

public class HomeController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/saveUser")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody RegisterUserRecord registerUserrecord){
        ServiceResponse<String> response = userServiceImpl.registerUser(registerUserrecord);
        if (response.getData() != null) {
            //test commit
            return new ResponseEntity<>(new ApiResponse("success",response.getData(), null),
                    HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ApiResponse("error", null, new Error(response.getMessage())),
                    HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestParam String userLoginId, @RequestParam String userLoginKey) {
        Optional<User> userOptional= userRepository.findByUserLoginId(userLoginId);
        if(userOptional.isEmpty()){
            ApiResponse apiResponse = new ApiResponse("error",null,new Error("Email Id doesn't exist. Please, signup first!"));
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        User existingUser = userOptional.get();
        String storedEncodedKey = existingUser.getUserLoginkey();

        boolean isSame = passwordEncoder.matches(userLoginKey, storedEncodedKey);
        System.out.println(isSame);

        if (isSame) {
            this.doAuthenticate(userLoginId, userLoginKey);

            UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginId);
            String token = this.helper.generateToken(userDetails);

            ApiResponse apiResponse = new ApiResponse("success",token,null);

            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }
        ApiResponse apiResponse = new ApiResponse("error",null,new Error("Incorrect id or key. Please, try with correct credentials!"));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users")
    public List<User> getUser() {
        System.out.println("Getting users");
        return userServiceImpl.getUsers();
    }

    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                password);
        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }
}
