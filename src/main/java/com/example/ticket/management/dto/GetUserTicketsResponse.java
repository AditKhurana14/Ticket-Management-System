package com.example.ticket.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserTicketsResponse {
    private UUID id;
    private List<UUID> createdTickets;
    private List<UUID> AssignedTickets;


}
