package com.example.library.code.services.iservices;

import com.example.library.code.data.theloai.CapNhatTheLoaiDto;
import com.example.library.code.data.theloai.ThemTheLoaiDto;
import com.example.library.code.models.entities.TheLoai;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITheLoaiService {
//    LayDanhSachTheLoaiDto danhSachTheLoai();
    List<TheLoai> danhSachTheLoai();

    TheLoai timTheLoaiTheoMa(int id);

    ThemTheLoaiDto themTheLoai(ThemTheLoaiDto themTheLoaiDto);

    CapNhatTheLoaiDto capNhatTheLoaiDto(CapNhatTheLoaiDto capNhatTheLoaiDto, int id);

    TheLoai xoaTheLoaiById(int id);
}

