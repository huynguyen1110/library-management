package com.example.library.code.models.entities;

import com.example.library.code.models.enums.HinhThucThanhToan;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Data
@Setter
@Getter
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
    public Boolean daXoa;
    @OneToOne
    public TheThuVien theThuVien;
    @OneToOne
    public TaiKhoan taiKhoan;
    @ToString.Exclude
    @OneToOne
    public GioHang gioHang;

}
