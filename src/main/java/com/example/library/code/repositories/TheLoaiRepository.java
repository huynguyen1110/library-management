package com.example.library.code.repositories;

import com.example.library.code.models.entities.TaiKhoan;
import com.example.library.code.models.entities.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheLoaiRepository extends JpaRepository<TheLoai, Integer> {
    @Query(value = "SELECT tl  FROM TheLoai tl WHERE tl.maTheLoai =:id")
    TheLoai timTheLoaiTheoMa(@Param("id") Integer id);
}
