package com.example.ticket.management.dto;

import com.example.ticket.management.utils.TicketCategoryEnum;
import com.example.ticket.management.utils.TicketPriorityEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketRequestDTO {
    private String title;
    private String description;
    private TicketPriorityEnum ticketPriority;
    private TicketCategoryEnum ticketCategory;
    private UUID createdBy;
    private UUID assignedTo;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDate dueDate;


}
