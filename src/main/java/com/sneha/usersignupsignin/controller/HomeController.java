package com.sneha.usersignupsignin.controller;

import com.sneha.usersignupsignin.entity.User;
import com.sneha.usersignupsignin.payload.ApiResponse;
import com.sneha.usersignupsignin.payload.Error;
import com.sneha.usersignupsignin.payload.IsValidResponse;
import com.sneha.usersignupsignin.payload.ServiceResponse;
import com.sneha.usersignupsignin.record.RegisterUserRecord;
import com.sneha.usersignupsignin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")

public class HomeController {



    @Autowired
    private UserServiceImpl userServiceImpl;
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


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUser(@RequestParam String userLoginId, @RequestParam String userLoginKey) {
        System.out.println("Getting users");
        IsValidResponse isValidResponse = userServiceImpl.isValidUser(userLoginId, userLoginKey);
        if(isValidResponse.isSuccess()){
            return new ResponseEntity<>(userServiceImpl.getUsers(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }
}
