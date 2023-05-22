package com.example.library.code.data.sach;

import com.example.library.code.models.entities.NhaXuatBan;
import com.example.library.code.models.entities.TacGia;
import com.example.library.code.models.entities.TheLoai;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CapNhapSachDto {
    public String tenSach;
    public Integer soLuong;
    public LocalDate ngayXuatBan;
    public Double giaTien;
    public TheLoai theLoai;
    public NhaXuatBan nhaXuatBan;
    private String image;
    public TacGia tacGia;
}
