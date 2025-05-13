package com.example.ticket.management.model;

import com.example.ticket.management.utils.TicketCategoryEnum;
import com.example.ticket.management.utils.TicketPriorityEnum;
import com.example.ticket.management.utils.TicketStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "Tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID ticketId;
    @Column(name = "Title", nullable = false)
    private String title;
    @Column(name = "Description",nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "Priority",nullable = false)
    private TicketPriorityEnum ticketPriority;

    @Enumerated(EnumType.STRING)
    @Column(name = "Ticket_Status",nullable = false)
    private TicketStatusEnum ticketStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "Ticket_Category",nullable = false)
    private TicketCategoryEnum ticketCategory;
    @ManyToOne
    @JoinColumn(name = "Assigned_To")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "Created_By")
    private User createdBy;

    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL,orphanRemoval = true)
    private List <Comments> comments;

    @ManyToMany
    @JoinTable(
            name = "Ticket_Tags",
            joinColumns = @JoinColumn(name = "Ticket_Id"),
            inverseJoinColumns = @JoinColumn(name = "Tag_Id")

    )
    private Set<Tag> tags=new HashSet<>();


    @Column(name = "Created_At")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "Updated_At")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "Due_Date",nullable = false)
    private LocalDate dueDate;

    @Column(name = "Closed_Date",nullable = true)
    private LocalDate closedDate;

    @Column(name = "Resolution_Comment" ,nullable = true)
    private String resolutionComment;







    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }
}
