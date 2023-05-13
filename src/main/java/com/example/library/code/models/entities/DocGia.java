package com.example.library.code.models.entities;

import com.example.library.code.models.enums.HinhThucThanhToan;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class DocGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer maDocGia;
    public String ten;
    public Date ngaySinh;
    public String sdt;
    public String email;
    public String diaCHi;
    @OneToOne
    public TheThuVien theThuVien;
    @OneToOne
    public TaiKhoan taiKhoan;
    @OneToOne
    public GioHang gioHang;
}
