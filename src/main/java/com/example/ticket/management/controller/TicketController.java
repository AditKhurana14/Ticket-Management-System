package com.example.ticket.management.controller;

import com.example.ticket.management.dto.*;
import com.example.ticket.management.service.TicketServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ticket")

public class TicketController {
    @Autowired
    private TicketServiceImpl ticketService;

    @PostMapping("/createTicket")
    @Operation(summary = "Create A Ticket",description = "This method creates the Ticket")
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody TicketRequestDTO ticketRequestDTO){
        TicketResponseDTO savedTicket = ticketService.createTicket(ticketRequestDTO);
       return  ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);

    }

    @PutMapping("/closeTicket")
    @Operation(summary = "Close A Ticket",description = "This method Closes the Ticket")

    public ResponseEntity<MessageDTO> closeTicket(@RequestBody CloseTicketDTO closeTicketDTO){
        ticketService.closeTicket(closeTicketDTO);
        return   ResponseEntity.status(HttpStatus.OK).body(new MessageDTO("Ticket  Closed Successfully"));

    }
@GetMapping("/getTcketByID/{id}")
@Operation(summary = "Get Ticket By Passing  A Ticket ID",description = "This method Gives details od ticket with help of ID")

    public ResponseEntity<TicketResponseDTO> getTicketByID(@RequestParam UUID id){
    TicketResponseDTO retreviedTicketByID = ticketService.getTicketByID(id);
    return ResponseEntity.status(HttpStatus.OK).body(retreviedTicketByID);


}
@GetMapping("/getUser/{id}")
@Operation(summary = "Fetched  A User From TicketID",description = "This method Gives  the user from TicketID")

    public ResponseEntity<UserResponseDTO> getUserFromTicketId(@RequestParam  UUID ticketId){
    UserResponseDTO fetchedUserFromTicketID = ticketService.getUserFromTicketID(ticketId);
    return ResponseEntity.status(HttpStatus.OK).body(fetchedUserFromTicketID);

}

@PutMapping("/updateTicket/{id}")
@Operation(summary = "Updates  A Ticket",description = "This method Updates the Ticket")


public ResponseEntity<TicketResponseDTO> updateTicket(@RequestParam  UUID ticketId,@RequestBody TicketRequestDTO ticketRequestDTO){
    TicketResponseDTO ticketResponseDTO1 = ticketService.updateTicket(ticketId, ticketRequestDTO);
    return ResponseEntity.status(HttpStatus.OK).body(ticketResponseDTO1);

}





}
