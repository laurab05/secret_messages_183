package com.laura.secret_messages_backend.service;

import com.laura.secret_messages_backend.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.laura.secret_messages_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

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

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean login(String username, String rawPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) return false;
        return bCryptPasswordEncoder.matches(rawPassword, userOpt.get().getPassword());
    }
    
}
