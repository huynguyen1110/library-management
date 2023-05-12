package com.example.library.code.repositories;

import com.example.library.code.models.entities.PhieuMuon;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhieuMuonRepository extends JpaRepository<PhieuMuon, Integer> {
}
