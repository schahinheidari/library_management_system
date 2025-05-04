package com.example.library_management.repository;

import com.example.library_management.entity.Publisher;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherDao extends JpaRepository<Publisher, Integer> {
}
