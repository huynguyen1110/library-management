package com.example.library.code.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer maGioHang;
    public Double tongTien;
    public Boolean daThanhToan;
    @OneToOne
    public DocGia docGia;
    @ManyToMany
    @JoinTable(name = "gio_hang_sach",
            joinColumns = @JoinColumn(name = "ma_gio_hang"),
            inverseJoinColumns = @JoinColumn(name = "ma_sach"))
    public Set<Sach> sachs = new HashSet<>();
}
