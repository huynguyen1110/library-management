package com.example.library.code.services.serviceimp;

import com.example.library.code.data.GetChiTietSachDto;
import com.example.library.code.models.entities.Sach;
import com.example.library.code.models.entities.TacGia;
import com.example.library.code.repositories.SachRepository;
import com.example.library.code.services.iservices.ISachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SachService implements ISachService {

    @Autowired
    private SachRepository sachRepository;

    @Override
    public List<Sach> timSachMoi(LocalDate ngayBatDau) {
        List<Sach> sachMoi = sachRepository.timSachMoi(ngayBatDau);
        List<Sach> results = new ArrayList<>();
        if ( (sachMoi != null || sachMoi.size() != 0)) {
            if (sachMoi.size() >= 8) {
                for (int i = 0; i < 8; i++) {
                    results.add(sachMoi.get(i));
                }
            } else {
                return sachMoi;
            }
        }
        return results;
    }

    @Override
    public List<Sach> timTatCa() {
        List<Sach> tatCaSach = sachRepository.findAllByMaSachIsNotNull();
        List<Sach> results = new ArrayList<>();
        if (tatCaSach != null && tatCaSach.size() != 0 && tatCaSach.size() >=8) {
            for (int i = 0; i < 8; i++) {
                results.add(tatCaSach.get(i));
            }
        } else {
            return tatCaSach;
        }
        return  results;
    }

    @Override
    public List<Sach> timTheLoaiTruyenNgan(String theLoai) {
        List<Sach> sachTheoTheLoai = sachRepository.timTheoTheLoai(theLoai);
        List<Sach> results = new ArrayList<>();
        if (sachTheoTheLoai != null && sachTheoTheLoai.size() != 0 && sachTheoTheLoai.size() >=8) {
            for (int i = 0; i < 8; i++) {
                results.add(sachTheoTheLoai.get(i));
            }
        } else {
            return sachTheoTheLoai;
        }
        return  results;
    }

    @Override
    public GetChiTietSachDto timSachTheoId(int id) {
        Sach sach = sachRepository.timSachTheoId(id);
        GetChiTietSachDto getChiTietSachDto = new GetChiTietSachDto();
        getChiTietSachDto.tenSach = sach.tenSach;
        getChiTietSachDto.giaTien = sach.giaTien;
        getChiTietSachDto.ngayXuatBan = sach.ngayXuatBan;
        getChiTietSachDto.soLuong = sach.soLuong;
        getChiTietSachDto.theLoai = sach.theLoai.tenTheLoai;
        getChiTietSachDto.getImage();
        getChiTietSachDto.nhaXuatBan = sach.nhaXuatBan.tenNhaXuatBan;
        getChiTietSachDto.tacGia = sach.tacGia.ten;
        return getChiTietSachDto;
    }
}
