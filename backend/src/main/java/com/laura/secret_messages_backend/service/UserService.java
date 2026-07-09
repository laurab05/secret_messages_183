package com.laura.secret_messages_backend.service;

import com.laura.secret_messages_backend.model.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.laura.secret_messages_backend.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository; 
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User register(String username, String rawPassword) {
        String hashedPassword = bCryptPasswordEncoder.encode(rawPassword);

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already registered!");
        }
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new UserDetails() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return List.of();
                    }

                    @Override
                    public @Nullable String getPassword() {
                        return user.getPassword();
                    }

                    @Override
                    public String getUsername() {
                        return user.getUsername();
                    }
                }).orElseThrow();
    }
}
