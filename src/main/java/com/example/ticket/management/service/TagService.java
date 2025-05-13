package com.example.ticket.management.service;

import com.example.ticket.management.dto.TagRequestDTO;
import com.example.ticket.management.dto.TagResponseDTO;

import java.util.UUID;

public interface TagService {
    TagResponseDTO createTag (TagRequestDTO tagRequestDTO);
    void addTagToTicket(UUID tagId,UUID ticketID);
}
