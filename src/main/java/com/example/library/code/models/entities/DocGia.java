package com.example.library.code.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
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
