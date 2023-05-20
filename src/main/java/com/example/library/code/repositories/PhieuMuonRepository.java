package com.example.library.code.repositories;

import com.example.library.code.models.entities.PhieuMuon;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhieuMuonRepository extends JpaRepository<PhieuMuon, Integer> {

    @Query("SELECT pm FROM PhieuMuon pm  WHERE pm.docGia.maDocGia = :maDocGia")
    List<PhieuMuon> findByMaDocGiaWithSach(int maDocGia);

}
