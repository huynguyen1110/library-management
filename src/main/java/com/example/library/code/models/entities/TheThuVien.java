package com.example.library.code.models.entities;

import com.example.library.code.models.enums.HinhThucThanhToan;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class TheThuVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer maThe;
    public Date ngayBatDau;
    public Date ngayKetThuc;
}
