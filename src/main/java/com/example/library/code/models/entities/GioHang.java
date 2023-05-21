package com.example.library.code.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
//@Data
@Setter
@Getter
@RequiredArgsConstructor
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    public Integer maGioHang;
    public Double tongTien;
    public Boolean daThanhToan;
    @OneToOne
    public DocGia docGia;
    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "gio_hang_sach",
            joinColumns = @JoinColumn(name = "ma_gio_hang"),
            inverseJoinColumns = @JoinColumn(name = "ma_sach"))
    public Set<Sach> sachs = new HashSet<>();
}
