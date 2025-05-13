package com.example.ticket.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDTO {
    private UUID commentID;
    private String content;
    private UUID userId;
    private UUID ticketId;
}
