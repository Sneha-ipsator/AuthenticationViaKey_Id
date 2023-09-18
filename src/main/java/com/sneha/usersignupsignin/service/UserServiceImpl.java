package com.sneha.usersignupsignin.service;

import com.sneha.usersignupsignin.dto.RegisterUserDTO;
import com.sneha.usersignupsignin.entity.User;
import com.sneha.usersignupsignin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(RegisterUserDTO registerUserdto) {
        Optional<User> existingUserOpt = userRepository.findByEmail(registerUserdto.getEmail());

        if(existingUserOpt.isPresent()) {
            return "Email id already exists. Try with a new one.";
        }

        User newUser = new User();
        String userLoginId = UUID.randomUUID().toString();
        newUser.setUserLoginId(userLoginId);
        newUser.setEmail(registerUserdto.getEmail());
        newUser.setName(registerUserdto.getName());
        newUser.setPassword(registerUserdto.getPassword());
        String key = UUID.randomUUID().toString();
        newUser.setUserLoginkey(passwordEncoder.encode(key));

        userRepository.save(newUser);

        return "Your User id: "+userLoginId+" and key: "+key;

    }

    // login api 1 --> input -- username, password ---> Output -- id (from database), user key (on the moment create)
    // user key -- database save, return to user
//    public User login1(String email,String password)
//    {
//       User user=userRepository.findByEmail(email);
//        if (user != null) {
//
//            if (user.getPassword().equals(password)) {
//                String newKey = UUID.randomUUID().toString();
//
//                // Update the user's key in the database
////                user.setKey(newKey);
//                userRepository.save(user);
//                return user;
//            }
//        }
//        return null;
//    }
//

    public List<User> getUsers() {
//		return this.store;
        return userRepository.findAll();
    }

//    public ResponseEntity<?> login1(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            String token = jwtUtil.generateToken(user.getUserId(), user.getKey());
//            UserResponseDTO responseDTO = new UserResponseDTO(user.getUserId(), user.getKey(), token);
//            return ResponseEntity.ok(responseDTO);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//        }
//    }





//    public User login(Integer userId,String key){
//        Optional<User> userOptional = userRepository.findById(userId);
//        if(userOptional.isPresent()){
//            User existingUser = userOptional.get();
//            if(existingUser.getKey().equals(key)){
//               return existingUser;
//            }
//        }
//        return null;
//        if (user != null && user.getPassword().equals(password)) {
//            return user;
//        }
//        return null;

  //  }
}
