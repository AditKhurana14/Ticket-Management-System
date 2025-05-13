package com.example.ticket.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CloseTicketDTO {
    private UUID ticketID;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")

    private String resolutionComment;
}
