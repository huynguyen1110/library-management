package com.example.library.code.services.iservices;

import com.example.library.code.data.giohang.LayGioHangDto;
import com.example.library.code.models.entities.GioHang;
import org.springframework.stereotype.Service;

@Service
public interface IGioHangService {

    GioHang themSachVaoGioHang(int maSach, int maTk);

    LayGioHangDto layGioHangTheoMaTk(int maTk);

    void xoaSachKhoiGioHang(int maSach, int maTk);

    void xoaGioHangByMaTk(int maDocGia);
}
