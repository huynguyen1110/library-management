package com.example.library.code.repositories;

import com.example.library.code.models.entities.TaiKhoan;
import com.example.library.code.services.serviceimp.TaiKhoanService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {

    TaiKhoan findByTenTk(String tenTk);

    TaiKhoan findByMaTk(int maTk);
}
