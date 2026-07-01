package com.laura.secret_messages_backend.service;

import com.laura.secret_messages_backend.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.laura.secret_messages_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository; 
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    public User register(String username, String rawPassword) {
        String hashedPassword = bCryptPasswordEncoder.encode(rawPassword);
        User user = new User();

        user.setUsername(username);
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }
    
}
