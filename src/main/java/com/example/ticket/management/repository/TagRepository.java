package com.example.ticket.management.repository;

import com.example.ticket.management.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {

}
