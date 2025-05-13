package com.example.ticket.management.service;

import com.example.ticket.management.dto.GetUserTicketsResponse;
import com.example.ticket.management.dto.UserRequestDTO;
import com.example.ticket.management.dto.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDTO createUser (UserRequestDTO userRequestDTO);
    void updateUser (UUID id,UserRequestDTO userRequestDTO);
    void deleteUser (UUID id);
    GetUserTicketsResponse getTicket(UUID id);
    List<UserResponseDTO> getAllUsers();

}
