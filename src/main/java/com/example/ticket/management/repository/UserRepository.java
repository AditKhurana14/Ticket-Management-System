package com.example.ticket.management.repository;

import com.example.ticket.management.dto.UserResponseDTO;
import com.example.ticket.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {

    boolean existsByEmail(String email);
    Optional<User> findById(UUID id);
    void deleteById(UUID id);
    Optional<User>findByEmail(String email);



}
