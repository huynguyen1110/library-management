package com.example.library.code.models.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
    public Date ngayXuatBan;
    public Double giaTien;
    @OneToOne
    public TheLoai theLoai;
    @OneToOne
    public NhaXuatBan nhaXuatBan;
    @Lob
    private byte[] image;
    @ManyToMany
    @JoinTable(name = "sach_tacgia",
            joinColumns = @JoinColumn(name = "ma_sach"),
            inverseJoinColumns = @JoinColumn(name = "ma_tac_gia"))
    public Set<TacGia> tacGias = new HashSet<>();
    @ManyToMany(mappedBy = "sachs")
    public Set<PhieuMuon> phieuMuons = new HashSet<>();
    @ManyToMany(mappedBy = "sachs")
    public Set<PhieuTra> phieuTras = new HashSet<>();
    @ManyToMany(mappedBy = "sachs")
    public Set<GioHang> gioHangs = new HashSet<>();

}
