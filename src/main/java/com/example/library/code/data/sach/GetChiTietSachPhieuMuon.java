package com.example.library.code.data.sach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class GetChiTietSachPhieuMuon {
    public Integer maSach;
    public String tenSach;
    public String tacGia;
}
