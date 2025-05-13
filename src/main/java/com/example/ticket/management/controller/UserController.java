package com.example.ticket.management.controller;

import com.example.ticket.management.dto.GetUserTicketsResponse;
import com.example.ticket.management.dto.MessageDTO;
import com.example.ticket.management.dto.UserRequestDTO;
import com.example.ticket.management.dto.UserResponseDTO;
import com.example.ticket.management.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/createUser")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create A User",description = "This method creates the User")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO){

        UserResponseDTO savedUser = userService.createUser(userRequestDTO);
       return  ResponseEntity.status(HttpStatus.OK).body(savedUser);



    }
    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    @Operation(summary = "Update a User",description = "This method Updates the User")

    public ResponseEntity<MessageDTO> updateUser(@PathVariable UUID id, @RequestBody UserRequestDTO userRequestDTO){
        userService.updateUser(id,userRequestDTO);
       return   ResponseEntity.status(HttpStatus.OK).body(new MessageDTO("User Updated Successfully"));

    }

    @DeleteMapping ("/deleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    @Operation(summary = "Delete a User",description = "This method Deletes the User")

    public ResponseEntity<MessageDTO> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return   ResponseEntity.status(HttpStatus.OK).body(new MessageDTO("User Deleted Successfully"));

    }

    @GetMapping("getTickets/{id}")

    @Operation(summary = "Gives Tickets Made By User Using UserID",description = "This method Tickets made or assigned to user  the User")

    public ResponseEntity<GetUserTicketsResponse> getTicketsByUserID(@RequestParam UUID userID){
        GetUserTicketsResponse retrievedTicket = userService.getTicket(userID);
       return  ResponseEntity.status(HttpStatus.OK).body(retrievedTicket);
    }


    @GetMapping("/getUsers")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")


    @Operation(summary = "Gives All Users",description = "This method gives all users")

    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        List<UserResponseDTO> allUsers = userService.getAllUsers();
        return  ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }
}
