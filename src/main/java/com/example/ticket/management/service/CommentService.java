package com.example.ticket.management.service;

import com.example.ticket.management.dto.CommentRequestDTO;
import com.example.ticket.management.dto.CommentResponseDTO;

import java.util.UUID;

public interface CommentService {

    CommentResponseDTO addComment(CommentRequestDTO commentRequestDTO);
    CommentResponseDTO updateComment(UUID commentID,CommentRequestDTO commentRequestDTO);
}
