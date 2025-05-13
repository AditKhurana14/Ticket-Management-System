package com.example.ticket.management.dto;

import com.example.ticket.management.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private User.Role role;
}
