package com.example.ticket.management.controller;

import com.example.ticket.management.dto.AuthRequest;
import com.example.ticket.management.dto.MessageDTO;
import com.example.ticket.management.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<MessageDTO> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            String token = jwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(new MessageDTO(token));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageDTO("Invalid username/password"));
        }
    }
}
