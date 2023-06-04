package com.example.library.code.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer maTheLoai;
    public String tenTheLoai;
    public Boolean daXoa;
}
