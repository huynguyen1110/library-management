package com.example.library.code.repositories;

import com.example.library.code.models.entities.PhieuMuon;
import com.example.library.code.models.entities.PhieuTra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhieuTraRepository extends JpaRepository<PhieuTra, Integer> {

    @Query("SELECT pt FROM PhieuTra pt  WHERE pt.docGia.maDocGia = :maDocGia")
    List<PhieuTra> findByMaDocGiaWithSach(int maDocGia);
}
