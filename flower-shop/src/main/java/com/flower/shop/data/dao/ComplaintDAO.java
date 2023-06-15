package com.flower.shop.data.dao;

import com.flower.shop.data.models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.UUID;

@Repository
public interface ComplaintDAO extends JpaRepository<Complaint, UUID>{
    Optional<Complaint> findById(UUID complaintId);
}
