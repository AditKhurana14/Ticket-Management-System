package com.example.ticket.management.service;

import com.example.ticket.management.dto.TagRequestDTO;
import com.example.ticket.management.dto.TagResponseDTO;
import com.example.ticket.management.exception.TagNotFoundExcepton;
import com.example.ticket.management.exception.TicketNotFoundException;
import com.example.ticket.management.model.Tag;
import com.example.ticket.management.model.Ticket;
import com.example.ticket.management.repository.TagRepository;
import com.example.ticket.management.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public TagResponseDTO createTag(TagRequestDTO tagRequestDTO) {
        Tag tagToSave = mapper.map(tagRequestDTO, Tag.class);
        Tag savedTag = tagRepository.save(tagToSave);

        return mapper.map(savedTag, TagResponseDTO.class);
    }

    @Override
    public void addTagToTicket(UUID tagId, UUID ticketID) {
        Ticket retrievedTicket = ticketRepository.findById(ticketID).orElseThrow(() -> new TicketNotFoundException("No Ticket Found with Given ID"));
        Tag retrievedTag = tagRepository.findById(tagId).orElseThrow(() -> new TagNotFoundExcepton("No Tag Found with Given ID"));
        retrievedTicket.getTags().add(retrievedTag);
        retrievedTag.getTickets().add(retrievedTicket);
        ticketRepository.save(retrievedTicket);
        tagRepository.save(retrievedTag);





    }
}
