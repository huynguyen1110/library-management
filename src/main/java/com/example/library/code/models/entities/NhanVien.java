package com.example.library.code.models.entities;

import com.example.library.code.models.enums.ChucVu;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer maNhanVien;
    public String ten;
    public Date ngaySinh;
    public String sdt;
    public String email;
    @Enumerated(EnumType.STRING)
    public ChucVu chucVu;
    @OneToOne
    public TaiKhoan taiKhoan;
}
