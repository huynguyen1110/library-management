package com.example.library.code.models.entities;

import com.example.library.code.models.enums.HinhThucThanhToan;
import com.example.library.code.models.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer maTk;
    public String tenTk;
    public String matKhau;
    @Enumerated(EnumType.STRING)
    public Role role;
    public boolean daXoa;
}
