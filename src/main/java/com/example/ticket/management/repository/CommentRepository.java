package com.example.ticket.management.repository;

import com.example.ticket.management.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CommentRepository extends JpaRepository<Comments, UUID> {
}
