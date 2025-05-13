package com.example.ticket.management.service;

import com.example.ticket.management.dto.CommentRequestDTO;
import com.example.ticket.management.dto.CommentResponseDTO;
import com.example.ticket.management.exception.CommentNotFound;
import com.example.ticket.management.exception.TicketNotFoundException;
import com.example.ticket.management.exception.UserNotFoundException;
import com.example.ticket.management.model.Comments;
import com.example.ticket.management.model.Ticket;
import com.example.ticket.management.model.User;
import com.example.ticket.management.repository.CommentRepository;
import com.example.ticket.management.repository.TicketRepository;
import com.example.ticket.management.repository.UserRepository;
import com.example.ticket.management.utils.BeanMapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceimpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BeanMapperUtils beanMapperUtils;



    @Autowired
   private TicketRepository ticketRepository;
    @Override
    public CommentResponseDTO addComment(CommentRequestDTO commentRequestDTO) {
        User fetchedUser = userRepository.findById(commentRequestDTO.getUserId())
                .orElseThrow(()->new UserNotFoundException("No USer Found with Given Id"));

        Ticket fetchedTicket = ticketRepository.findById(commentRequestDTO.getTicketId())
                .orElseThrow(()->new TicketNotFoundException("No Ticket Found with Given Id"));

        Comments comment = new Comments();
        comment.setContent(commentRequestDTO.getContent());
        comment.setCreatedAt(LocalDate.now());
        comment.setTicket(fetchedTicket);
        comment.setUserID(fetchedUser.getId());
        Comments savedComment = commentRepository.save(comment);
        comment.setCommentId(savedComment.getCommentId());
       return mapper.map(comment,CommentResponseDTO.class);

    }

    @Override
    public CommentResponseDTO updateComment(UUID commentID, CommentRequestDTO commentRequestDTO) {

        User fetchedUser = userRepository.findById(commentRequestDTO.getUserId())
                .orElseThrow(()->new UserNotFoundException("No USer Found with Given Id"));

        Ticket fetchedTicket = ticketRepository.findById(commentRequestDTO.getTicketId())
                .orElseThrow(()->new TicketNotFoundException("No Ticket Found with Given Id"));

        Comments fetchedComment = commentRepository.findById(commentID).orElseThrow(()->new CommentNotFound("No Comment Found with Given Id"));

        beanMapperUtils.copyNonNullProperties(commentRequestDTO, fetchedComment);
        Comments savedComment = commentRepository.save(fetchedComment);
        Comments comment = new Comments();
        comment.setContent(commentRequestDTO.getContent());
        comment.setCreatedAt(LocalDate.now());
        comment.setTicket(fetchedTicket);
        comment.setUserID(fetchedUser.getId());
        comment.setCommentId(savedComment.getCommentId());

        return mapper.map(comment,CommentResponseDTO.class);



    }
}
