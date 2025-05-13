package com.example.ticket.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "Name" ,nullable = false)
    private String name;

    @Column(name = "Email" ,nullable = false,unique = true)
    private String email;

    @Column(name = "Password" ,nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "Role",nullable = false)
    private Role role;

    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL)
    private List<Ticket> createdTickets;

    @OneToMany(mappedBy = "assignedTo",cascade = CascadeType.ALL)
    private List<Ticket> assignedTickets;


    public enum Role{
        USER,
        AGENT,
        ADMIN
    }








}
