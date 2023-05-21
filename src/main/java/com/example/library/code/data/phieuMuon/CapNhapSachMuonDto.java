package com.example.library.code.data.phieuMuon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CapNhapSachMuonDto {
    public int idPhieuMuon;
    public int idSach;
}
