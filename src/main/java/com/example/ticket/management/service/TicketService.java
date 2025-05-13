package com.example.ticket.management.service;

import com.example.ticket.management.dto.CloseTicketDTO;
import com.example.ticket.management.dto.TicketRequestDTO;
import com.example.ticket.management.dto.TicketResponseDTO;
import com.example.ticket.management.dto.UserResponseDTO;

import java.util.UUID;

public interface TicketService {
    TicketResponseDTO createTicket (TicketRequestDTO ticketRequestDTO);
    void closeTicket(CloseTicketDTO closeTicketDTO);
    TicketResponseDTO getTicketByID(UUID id);
    UserResponseDTO getUserFromTicketID (UUID ticketID);
    TicketResponseDTO updateTicket(UUID ticketId,TicketRequestDTO ticketRequestDTO );

}
