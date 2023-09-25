package com.sneha.usersignupsignin.controller;

import com.sneha.usersignupsignin.payload.ApiResponse;
import com.sneha.usersignupsignin.payload.Error;
import com.sneha.usersignupsignin.payload.ServiceResponse;
import com.sneha.usersignupsignin.record.RegisterUserRecord;
import com.sneha.usersignupsignin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

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
}
