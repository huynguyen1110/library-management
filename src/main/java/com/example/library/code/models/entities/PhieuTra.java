package com.example.library.code.models.entities;

import com.example.library.code.models.enums.HinhThucMuonTra;
import com.example.library.code.models.enums.HinhThucThanhToan;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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
    public Integer soLuong;
    @OneToOne
    public NhanVien nhanVien;
    @OneToOne
    public DocGia docGia;
    @ManyToMany
    @JoinTable(name = "phieu_tra_sach",
            joinColumns = @JoinColumn(name = "phieu_tra_id"),
            inverseJoinColumns = @JoinColumn(name = "sach_id"))
    private Set<Sach> sachs = new HashSet<>();
    public boolean trang_thai;


}
