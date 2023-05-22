package com.example.library.code.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class Sach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer maSach;
    public String tenSach;
    public Integer soLuong;
    public LocalDate ngayXuatBan;
    public Double giaTien;
    @OneToOne
    public TheLoai theLoai;
    @OneToOne
    public NhaXuatBan nhaXuatBan;
    public String image;
    @ManyToOne
    @JoinColumn()
    public TacGia tacGia;
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "sachs")
    public Set<PhieuMuon> phieuMuons = new HashSet<>();
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "sachs")
    public Set<PhieuTra> phieuTras = new HashSet<>();
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "sachs")
    public Set<GioHang> gioHangs = new HashSet<>();
}
