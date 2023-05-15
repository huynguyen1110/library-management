package com.example.library.code.repositories;

import com.example.library.code.models.entities.Sach;
import com.example.library.code.models.entities.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SachRepository extends JpaRepository<Sach, Integer> {

    @Query("select s from Sach s where s.ngayXuatBan >= :ngayBatDau")
    List<Sach> timSachMoi(LocalDate ngayBatDau);

    List<Sach> findAllByMaSachIsNotNull();

    @Query("SELECT s FROM Sach s WHERE s.theLoai.tenTheLoai = :theLoai")
    List<Sach> timTheoTheLoai(String theLoai);

    @Query("select s from Sach s where s.maSach = :id")
    Sach timSachTheoId(int id);
}
