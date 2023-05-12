package com.example.library.code.repositories;

import com.example.library.code.models.entities.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
}
