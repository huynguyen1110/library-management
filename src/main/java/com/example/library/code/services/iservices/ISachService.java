package com.example.library.code.services.iservices;

import com.example.library.code.data.sach.GetChiTietSachDto;
import com.example.library.code.data.sach.ThemSachDto;
import com.example.library.code.models.entities.Sach;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ISachService {

    List<Sach> timSachMoi(LocalDate ngayBatDau);

    public List<Sach> timTatCa();

    public List<Sach> timTheLoaiTruyenNgan(String theLoai);

    public GetChiTietSachDto timSachTheoId(int id);

    public Sach themSach(ThemSachDto sachDto);

    public Sach xoaSach(int id);

    public Sach capNhap(int id, ThemSachDto sachDto);
}
