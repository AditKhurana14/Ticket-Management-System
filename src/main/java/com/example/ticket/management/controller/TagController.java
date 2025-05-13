package com.example.ticket.management.controller;

import com.example.ticket.management.dto.MessageDTO;
import com.example.ticket.management.dto.TagRequestDTO;
import com.example.ticket.management.dto.TagResponseDTO;
import com.example.ticket.management.service.TagServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tag")

public class TagController {
    @Autowired
    private TagServiceImpl tagService;

    @PostMapping("/createTag")
    @Operation(summary = "Create  A Tag",description = "This method Creates the Tag")

    public ResponseEntity<TagResponseDTO> createTag(@RequestBody TagRequestDTO tagRequestDTO){
        TagResponseDTO savedTag = tagService.createTag(tagRequestDTO);
        return  ResponseEntity.status(HttpStatus.OK).body(savedTag);

    }

    @PostMapping("/addTagToTicket/{tagId}/{ticketId}")
    @Operation(summary = "Add Tag To Ticket",description = "This method Updates Add Tag To Ticket")

    public ResponseEntity<MessageDTO> addTagToTicket(@RequestParam UUID tagId, @RequestParam UUID ticketID) {
         tagService.addTagToTicket(tagId,ticketID);
        return  ResponseEntity.status(HttpStatus.OK).body(new MessageDTO("Tag Added To Ticket"));

    }
}
