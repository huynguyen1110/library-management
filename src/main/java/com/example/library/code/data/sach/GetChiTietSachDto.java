package com.example.library.code.data.sach;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class GetChiTietSachDto {

    public Integer maSach;
    public String tenSach;
    public Integer soLuong;
    public LocalDate ngayXuatBan;
    public Double giaTien;
    public String theLoai;
    public String nhaXuatBan;
    public String image;
    public String tacGia;
}
