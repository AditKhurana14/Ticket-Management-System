package com.example.ticket.management.service;

import com.example.ticket.management.dto.GetUserTicketsResponse;
import com.example.ticket.management.dto.UserRequestDTO;
import com.example.ticket.management.dto.UserResponseDTO;
import com.example.ticket.management.exception.EmailAlreadyExistsException;
import com.example.ticket.management.exception.UserNotFoundException;
import com.example.ticket.management.model.User;
import com.example.ticket.management.repository.TicketRepository;
import com.example.ticket.management.repository.UserRepository;
import com.example.ticket.management.utils.BeanMapperUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BeanMapperUtils beanMapperUtils;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        if(userRepository.existsByEmail(userRequestDTO.getEmail()))
            throw new EmailAlreadyExistsException("Email Already Exists");

        User userEntity = mapper.map(userRequestDTO, User.class);
        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        userEntity.setPassword(encodedPassword);

        User savedUser = userRepository.save(userEntity);
        return  mapper.map(savedUser, UserResponseDTO.class);


    }

    @Override
    public void updateUser(UUID id, UserRequestDTO userRequestDTO) {

        User fetchedUser = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User Not Found "));
        beanMapperUtils.copyNonNullProperties(userRequestDTO,fetchedUser);
        userRepository.save(fetchedUser);




    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        User fetchedUser = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User Not Found "));
        userRepository.deleteById(id);


    }

    @Override
    public GetUserTicketsResponse getTicket(UUID id) {
        List<UUID> createdUUID = ticketRepository.fetchTicketIdsByCreatedBy(id);
        List<UUID> assignedUUID = ticketRepository.fetchTicketIdsByAssignedBy(id);
        GetUserTicketsResponse getUserTicketsResponse=new GetUserTicketsResponse();
        getUserTicketsResponse.setId(id);
        getUserTicketsResponse.setCreatedTickets(createdUUID);
        getUserTicketsResponse.setAssignedTickets(assignedUUID);
        return getUserTicketsResponse;




    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(user -> mapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }
}
