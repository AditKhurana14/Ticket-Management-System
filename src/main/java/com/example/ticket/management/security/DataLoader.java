package com.example.ticket.management.security;

import com.example.ticket.management.model.User;
import com.example.ticket.management.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DataLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            User admin = User.builder()

                    .name("Admin")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(User.Role.ADMIN)
                    .build();

            userRepository.save(admin);
        }
    }
}