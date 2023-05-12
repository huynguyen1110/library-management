package com.example.library.code.repositories;

import com.example.library.code.models.entities.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheLoaiRepository extends JpaRepository<TaiKhoan, Integer> {
}
