package com.example.ticket.management.dto;

import com.example.ticket.management.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private User.Role role;
}
