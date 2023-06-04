package com.example.library.code.services.iservices;

import com.example.library.code.data.sach.GetChiTietSachDto;
import com.example.library.code.data.sach.ThemSachDto;
import com.example.library.code.models.entities.Sach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ISachService {

    List<Sach> timSachMoi(LocalDate ngayBatDau);

    List<Sach> timTatCa();

    List<Sach> adminTimTatCaSach();

    List<Sach> timTheLoaiTruyenNgan(String theLoai);

    GetChiTietSachDto timSachTheoId(int id);

    Sach timSachTheoId2(int id);

    Page<Sach> timTatCaSachCoPhanTrang(int pageNumber, String orderBy);

    Page<Sach> laySachTheoTheLoaiCoPhanTrang(int pageNumber, String theLoai, String orderBy);

    public Sach themSach(ThemSachDto sachDto);

    public Sach xoaSach(int id);

    public Sach capNhap(int id, ThemSachDto sachDto);

    public double tinhTien(int[] idSachs);
}
