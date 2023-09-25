package com.sneha.usersignupsignin.service;

import com.sneha.usersignupsignin.payload.ServiceResponse;
import com.sneha.usersignupsignin.record.RegisterUserRecord;
import com.sneha.usersignupsignin.entity.User;

import java.util.List;

public interface UserService {
    public ServiceResponse<String> registerUser(RegisterUserRecord registerUserRecord);

    public List<User> getUsers();

    User getUserByLoginId(String id);
}
