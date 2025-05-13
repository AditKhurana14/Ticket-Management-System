package com.example.ticket.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comments {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID commentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ticket_Id",nullable = false)
    private Ticket ticket;
    @Column(name = "User_ID",nullable = false)
    private UUID userID;

    @Column(name = "Content" ,columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(name = "Created_Date")
    private LocalDate createdAt;

}
