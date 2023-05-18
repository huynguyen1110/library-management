package com.example.library.code.services.iservices;

import com.example.library.code.data.sach.GetChiTietSachDto;
import com.example.library.code.models.entities.Sach;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ISachService {

    List<Sach> timSachMoi(LocalDate ngayBatDau);

    List<Sach> timTatCa();

    List<Sach> timTheLoaiTruyenNgan(String theLoai);

    GetChiTietSachDto timSachTheoId(int id);

    Page<Sach> timTatCaSachCoPhanTrang(int pageNumber, String orderBy);
}
