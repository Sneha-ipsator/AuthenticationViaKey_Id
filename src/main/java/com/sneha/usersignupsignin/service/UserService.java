package com.sneha.usersignupsignin.service;

import com.sneha.usersignupsignin.entity.User;
import com.sneha.usersignupsignin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
       User user1= userRepository.save(user);
       return user1;
    }

    // login api 1 --> input -- username, password ---> Output -- id (from database), user key (on the moment create)
    // user key -- database save, return to user
    public User login1(String username,String password)
    {
       User user=userRepository.findByUsername(username);
        if (user != null) {

            if (user.getPassword().equals(password)) {
                String newKey = UUID.randomUUID().toString();

                // Update the user's key in the database
                user.setKey(newKey);
                userRepository.save(user);
                return user;
            }
        }
        return null;
    }




    public User login(Integer userId,String key){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User existingUser = userOptional.get();
            if(existingUser.getKey().equals(key)){
               return existingUser;
            }
        }
        return null;
//        if (user != null && user.getPassword().equals(password)) {
//            return user;
//        }
//        return null;

    }
}
