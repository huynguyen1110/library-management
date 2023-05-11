package com.example.library.code.models.entities;

import com.example.library.code.models.enums.HinhThucMuonTra;
import com.example.library.code.models.enums.HinhThucThanhToan;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class PhieuTra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer maPhieuTra;
    public LocalDateTime ngayTra;
    @Enumerated(EnumType.STRING)
    public HinhThucThanhToan hinhThucThanhToan;
    @Enumerated(EnumType.STRING)
    public HinhThucMuonTra hinhThucTra;
    public Double tongTien;
    @OneToOne
    public NhanVien nhanVien;
    @OneToOne
    public DocGia docGia;
    @ManyToMany
    @JoinTable(name = "phieu_tra_sach",
            joinColumns = @JoinColumn(name = "phieu_tra_id"),
            inverseJoinColumns = @JoinColumn(name = "sach_id"))
    private Set<Sach> sachs = new HashSet<>();
}
