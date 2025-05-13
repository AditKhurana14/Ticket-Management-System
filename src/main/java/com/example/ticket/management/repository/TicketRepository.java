package com.example.ticket.management.repository;

import com.example.ticket.management.dto.UserResponseDTO;
import com.example.ticket.management.model.Ticket;
import com.example.ticket.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    @Query("SELECT t.ticketId from Ticket t where t.createdBy.id=:userId")
    List<UUID> fetchTicketIdsByCreatedBy(@Param("userId") UUID id);
    @Query("SELECT t.ticketId from Ticket t where t.assignedTo.id=:userId")
    List<UUID> fetchTicketIdsByAssignedBy(@Param("userId") UUID id);
    @Query("select t.createdBy from Ticket t where t.ticketId=:ticketId")
    Optional<User> fetchUserFromTicketId(@Param("ticketId") UUID ticketId);
}
