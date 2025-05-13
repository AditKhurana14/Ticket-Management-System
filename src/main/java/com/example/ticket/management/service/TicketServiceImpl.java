package com.example.ticket.management.service;

import com.example.ticket.management.dto.CloseTicketDTO;
import com.example.ticket.management.dto.TicketRequestDTO;
import com.example.ticket.management.dto.TicketResponseDTO;
import com.example.ticket.management.dto.UserResponseDTO;
import com.example.ticket.management.exception.TicketNotFoundException;
import com.example.ticket.management.exception.UserNotFoundException;
import com.example.ticket.management.model.Ticket;
import com.example.ticket.management.model.User;
import com.example.ticket.management.repository.TicketRepository;
import com.example.ticket.management.repository.UserRepository;
import com.example.ticket.management.utils.BeanMapperUtils;
import com.example.ticket.management.utils.TicketStatusEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BeanMapperUtils beanMapperUtils;


    @Override
    public TicketResponseDTO createTicket(TicketRequestDTO ticketRequestDTO) {
        User createdByUser = userRepository.findById(ticketRequestDTO.getCreatedBy())
                .orElseThrow(()->new UserNotFoundException("No User Found For Which You Want To Create Ticket "));

        User assignedTo=null;
        if(ticketRequestDTO.getAssignedTo()!=null){
            assignedTo=userRepository.findById(ticketRequestDTO.getAssignedTo()).orElseThrow(()->new UserNotFoundException("No User Found For Which You Want To Assign The Ticket "));

        }
        Ticket ticket = mapper.map(ticketRequestDTO, Ticket.class);
        ticket.setCreatedBy(createdByUser);
        ticket.setAssignedTo(assignedTo);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setTicketStatus(TicketStatusEnum.OPEN);

        Ticket savedTicket = ticketRepository.save(ticket);
        TicketResponseDTO dtoConvertedTicket = mapper.map(savedTicket, TicketResponseDTO.class);
        dtoConvertedTicket.setCreatedBy(savedTicket.getCreatedBy().getId());
        if(savedTicket.getAssignedTo()!=null) {
            dtoConvertedTicket.setAssignedTo(savedTicket.getAssignedTo().getId());
        }
        return dtoConvertedTicket;



    }

    @Override
    public void closeTicket(CloseTicketDTO closeTicketDTO) {
        Ticket retrievedYicket = ticketRepository.findById(closeTicketDTO.getTicketID()).orElseThrow(()->new TicketNotFoundException("No Ticket Found with Given ID"));
        retrievedYicket.setTicketStatus(TicketStatusEnum.CLOSED);
        retrievedYicket.setResolutionComment(closeTicketDTO.getResolutionComment());
        retrievedYicket.setClosedDate(LocalDate.now());
        ticketRepository.save(retrievedYicket);



    }

    @Override
    public TicketResponseDTO getTicketByID(UUID id) {
        Ticket retrievedTicket = ticketRepository.findById(id).orElseThrow(()->new TicketNotFoundException("No Ticket Found with Given ID"));


        TicketResponseDTO retrievedDTO = mapper.map(retrievedTicket, TicketResponseDTO.class);
        retrievedDTO.setCreatedBy(retrievedTicket.getCreatedBy().getId());
        if(retrievedTicket.getAssignedTo()!=null){
            retrievedDTO.setAssignedTo(retrievedTicket.getAssignedTo().getId());

        }
        return retrievedDTO;
    }

    @Override
    public UserResponseDTO getUserFromTicketID(UUID ticketID) {
        User fetchedUser = ticketRepository.fetchUserFromTicketId(ticketID).orElseThrow(() -> new UserNotFoundException("No User Found With Ticket ID "));
        UserResponseDTO fetchedUserDTO = mapper.map(fetchedUser, UserResponseDTO.class);
        return fetchedUserDTO;

    }

    @Override
    public TicketResponseDTO updateTicket(UUID ticketId, TicketRequestDTO ticketRequestDTO) {
        Ticket retrievedTicket = ticketRepository.findById(ticketId).orElseThrow(()->new TicketNotFoundException("No Ticket Found with Given ID"));

        retrievedTicket.setUpdatedAt(LocalDateTime.now());

beanMapperUtils.copyNonNullProperties(ticketRequestDTO, retrievedTicket); // Corrected copy direction
        ticketRepository.save(retrievedTicket);


        TicketResponseDTO retrevedDTO = mapper.map(retrievedTicket, TicketResponseDTO.class);
        retrevedDTO.setCreatedBy(retrievedTicket.getCreatedBy().getId());
        if(retrievedTicket.getAssignedTo()!=null){
            retrevedDTO.setAssignedTo(retrievedTicket.getAssignedTo().getId());

        }

        return retrevedDTO;


    }
}
