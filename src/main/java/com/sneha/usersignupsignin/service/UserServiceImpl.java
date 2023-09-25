package com.sneha.usersignupsignin.service;

import com.sneha.usersignupsignin.entity.User;
import com.sneha.usersignupsignin.payload.IsValidResponse;
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
        String rawPassword = registerUserRecord.password();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        newUser.setPassword(encodedPassword);
        newUser.setRole(registerUserRecord.role());
        String key = UUID.randomUUID().toString();
        newUser.setUserLoginkey(passwordEncoder.encode(key));

        User savedUser = userRepository.save(newUser);

        ServiceResponse<String> response = new ServiceResponse<>(true,"Your User login Id: "+userLoginId +", and key: "+key,"Registration successful");
        return response;
    }


    //Testing
    public IsValidResponse isValidUser(String userLoginId, String userLoginKey) {
        Optional<User> userOptional = userRepository.findByUserLoginId(userLoginId);
        if(userOptional.isPresent())
        {

          User existingUser=userOptional.get();
          boolean isEqual=passwordEncoder.matches(userLoginKey,existingUser.getUserLoginkey());
          if(isEqual)
          {
                System.out.println("Matches");
                return new IsValidResponse(true,"Successful");
          }
          else{
              return new IsValidResponse(false,"Invalid Key");
          }
        }
        else{
            System.out.println("User not Present");
            return new IsValidResponse(false,"Invalid id");
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByLoginId(String loginId) {
        return userRepository.findByUserLoginId(loginId).orElse(new User());
    }
}
