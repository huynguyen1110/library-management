package com.example.library.code.services.serviceimp;

import com.example.library.code.data.sach.GetChiTietSachDto;
import com.example.library.code.data.sach.ThemSachDto;
import com.example.library.code.models.entities.Sach;
import com.example.library.code.models.entities.TacGia;
import com.example.library.code.repositories.SachRepository;
import com.example.library.code.services.iservices.ISachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<Sach> adminTimTatCaSach() {
        return sachRepository.findAll();
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
        getChiTietSachDto.maSach = sach.maSach;
        getChiTietSachDto.tenSach = sach.tenSach;
        getChiTietSachDto.giaTien = sach.giaTien;
        getChiTietSachDto.ngayXuatBan = sach.ngayXuatBan;
        getChiTietSachDto.soLuong = sach.soLuong;
        getChiTietSachDto.theLoai = sach.theLoai.tenTheLoai;
        getChiTietSachDto.image = sach.image;
        getChiTietSachDto.nhaXuatBan = sach.nhaXuatBan.tenNhaXuatBan;
        getChiTietSachDto.tacGia = sach.tacGia.ten;
        return getChiTietSachDto;
    }

    @Override
    public Sach timSachTheoId2(int id) {
        return sachRepository.findById(id).get();
    }

    @Override
    public Sach themSach(ThemSachDto sach) {
        Sach themSach = new Sach();
        themSach.setTenSach(sach.tenSach);
        themSach.setSoLuong(sach.soLuong);
        themSach.setNgayXuatBan(sach.ngayXuatBan);
        themSach.setGiaTien(sach.giaTien);
        themSach.setTheLoai(sach.theLoai);
        themSach.setNhaXuatBan(sach.nhaXuatBan);
        themSach.setImage(sach.getImage());
        themSach.setTacGia(sach.tacGia);

        sachRepository.save(themSach);
        return themSach;
    }

    @Override
    public Sach xoaSach(int id) {
        Optional<Sach> sach = sachRepository.findById(id);
        sachRepository.delete(sach.get());
        return sach.get();
    }

    @Override
    public Sach capNhap(int id, ThemSachDto sachDto) {
        Optional<Sach> sach = sachRepository.findById(id);
        sach.get().setTenSach(sachDto.tenSach);
        sach.get().setSoLuong(sachDto.soLuong);
        sach.get().setNgayXuatBan(sachDto.ngayXuatBan);
        sach.get().setGiaTien(sachDto.giaTien);
        sach.get().setTheLoai(sachDto.theLoai);
        sach.get().setNhaXuatBan(sachDto.nhaXuatBan);
        sach.get().setImage(sachDto.image);
        sach.get().setTacGia(sachDto.tacGia);
        sachRepository.save(sach.get());
        return sach.get();
    }

    @Override
    public double tinhTien(int[] idSachs) {
        double tongTien = 0;
        for(int i = 0; i < idSachs.length; i++){
            Sach sach = sachRepository.findById(idSachs[i]).get();
            tongTien += sach.giaTien;
        }
        return tongTien;
    }


    @Override
    public Page<Sach> timTatCaSachCoPhanTrang(int pageNumber, String orderBy) {
        Pageable pageable = PageRequest.of(pageNumber, 12, Sort.by(orderBy).ascending());
        if (orderBy == "tenSach") {
            return sachRepository.findAllByOrderByTenSachAsc(pageable);
        }
        if (orderBy == "ngayXuatBan") {
            return sachRepository.findAllByOrderByNgayXuatBanAsc(pageable);
        } else  {
            return sachRepository.findAllByOrderByGiaTienAsc(pageable);
        }
    }

    @Override
    public Page<Sach> laySachTheoTheLoaiCoPhanTrang(int pageNumber, String theLoai, String orderBy) {
        Pageable pageable = PageRequest.of(pageNumber, 12, Sort.by(orderBy).ascending());
        if ("tenSach".equals(orderBy)) {
            return sachRepository.findAllByTheLoai_TenTheLoaiOrderByTenSachAsc(theLoai, pageable);
        }
        if ("ngayXuatBan".equals(orderBy)) {
            return sachRepository.findAllByTheLoai_TenTheLoaiOrderByNgayXuatBanAsc(theLoai ,pageable);
        } else  {
            return sachRepository.findAllByTheLoai_TenTheLoaiOrderByGiaTienAsc(theLoai ,pageable);
        }
    }
}
