package com.example.library.code.data.phieuTra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CapNhapSachTraDto {

    public int maPhieuTra;

    public int maPhieuMuon;

    public int[] idSachs;
}
