package com.example.ticket.management.controller;

import com.example.ticket.management.dto.CommentRequestDTO;
import com.example.ticket.management.dto.CommentResponseDTO;
import com.example.ticket.management.service.CommentService;
import com.example.ticket.management.service.CommentServiceimpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comment")

public class CommentController {
    @Autowired
    private CommentServiceimpl commentService;
    @PostMapping("/createComment")
    @Operation(summary = "Create  A Comment",description = "This method Creates the Comment")

    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO){
        CommentResponseDTO commentResponseDTO = commentService.addComment(commentRequestDTO);
       return  ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDTO);



    }
    @PutMapping("/updateComment")
    @Operation(summary = "Update  A Comment",description = "This method Updates the Comment")

    public ResponseEntity<CommentResponseDTO> updateComment(@RequestParam UUID commentID , @RequestBody CommentRequestDTO commentRequestDTO){
        CommentResponseDTO updatedComment = commentService.updateComment(commentID, commentRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedComment);


    }
}
