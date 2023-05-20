package com.example.library.code.data.phieumuontra;

import com.example.library.code.models.entities.DocGia;
import com.example.library.code.models.entities.NhanVien;
import com.example.library.code.models.entities.Sach;
import com.example.library.code.models.enums.HinhThucMuonTra;
import com.example.library.code.models.enums.HinhThucThanhToan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LayPhieuMuonTraDto {

    public Integer maPhieuMuon;
    public LocalDateTime ngayMuon;
    public Double tongTien;
    public Integer soLuong;
    public DocGia docGia;
    private Set<Sach> sachMuon = new HashSet<>();
    public String trangThaiMuon;
    public Integer maPhieuTra;
    public LocalDateTime ngayTra;
    public Integer soLuongTra;
    private Set<Sach> sachTra = new HashSet<>();
    public String trangThaiTra;
}
