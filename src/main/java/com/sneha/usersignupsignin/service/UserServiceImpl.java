package com.sneha.usersignupsignin.service;

import com.sneha.usersignupsignin.entity.User;
import com.sneha.usersignupsignin.payload.ServiceResponse;
import com.sneha.usersignupsignin.record.RegisterUserRecord;
import com.sneha.usersignupsignin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ServiceResponse<String> registerUser(RegisterUserRecord registerUserRecord) {
        Optional<User> existingUserOpt = userRepository.findByEmail(registerUserRecord.email());

        if(existingUserOpt.isPresent()) {

            ServiceResponse<String> response = new ServiceResponse<>(false, null,
                    "Email Id already exists.Try with a new one.");
            return response;

        }

        User newUser = new User();
        String userLoginId = UUID.randomUUID().toString();
        newUser.setUserLoginId(userLoginId);
        newUser.setEmail(registerUserRecord.email());
        newUser.setName(registerUserRecord.name());
        newUser.setPassword(registerUserRecord.password());
        String key = UUID.randomUUID().toString();
        newUser.setUserLoginkey(passwordEncoder.encode(key));

        userRepository.save(newUser);

        ServiceResponse<String> response = new ServiceResponse<>(true,"Your User login Id: "+userLoginId +", and key: "+key,"Registration successful");
        return response;



    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
