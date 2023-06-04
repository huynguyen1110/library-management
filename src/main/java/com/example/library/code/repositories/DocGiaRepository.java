package com.example.library.code.repositories;

import com.example.library.code.models.entities.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
public interface DocGiaRepository extends JpaRepository<DocGia, Integer> {

    @Query("SELECT d FROM DocGia d WHERE d.taiKhoan.maTk = :maTk")
    DocGia findByMaTk(@Param("maTk") int maTk);

    DocGia findByTen(String ten);
}
