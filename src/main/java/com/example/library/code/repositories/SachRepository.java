package com.example.library.code.repositories;

import com.example.library.code.models.entities.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SachRepository extends JpaRepository<Sach, Integer> {

    @Query("select s from Sach s where s.ngayXuatBan >= :startDate and s.ngayXuatBan <= :endDate")
    public List<Sach> timSachMoi(@Param("startDate") LocalDate ngayBatDau, @Param("endDate") LocalDate ngayKetThuc);
}
