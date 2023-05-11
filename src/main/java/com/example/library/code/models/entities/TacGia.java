package com.example.library.code.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class TacGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer maTacGia;
    public String ten;
    public String diaChi;
    public String sdt;
    public String email;
    public Date ngaySinh;
    @ManyToMany(mappedBy = "tacGias")
    public Set<Sach> sachSet = new HashSet<>();
}
