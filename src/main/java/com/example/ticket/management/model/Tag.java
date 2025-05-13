package com.example.ticket.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Tag {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID tagId;
    @Column(nullable = false,unique = true)
    private String name;
    @ManyToMany(mappedBy = "tags")
    private Set<Ticket> tickets=new HashSet<>();

    //equals and hashcode implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(tagId, tag.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId);
    }



}
