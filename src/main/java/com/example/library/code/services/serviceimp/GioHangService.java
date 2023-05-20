package com.example.library.code.services.serviceimp;

import com.example.library.code.data.giohang.LayGioHangDto;
import com.example.library.code.data.sach.GetChiTietSachDto;
import com.example.library.code.models.entities.DocGia;
import com.example.library.code.models.entities.GioHang;
import com.example.library.code.models.entities.Sach;
import com.example.library.code.repositories.DocGiaRepository;
import com.example.library.code.repositories.GioHangRepository;
import com.example.library.code.repositories.SachRepository;
import com.example.library.code.services.iservices.IGioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.Ansi8BitColor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class GioHangService implements IGioHangService {

    @Autowired
    private SachRepository sachRepository;

    @Autowired
    private DocGiaRepository docGiaRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public GioHang themSachVaoGioHang(int maSach, int maTk) {
        DocGia docGia  = docGiaRepository.findByMaTk(maTk);
        Sach sach = sachRepository.timSachTheoId(maSach);
        if (isGioHangExist(maTk) == false){
            double giaSach = 0;
            giaSach += sach.giaTien;
            GioHang gioHang = new GioHang();
            gioHang.tongTien = giaSach;
            gioHang.maGioHang = docGia.maDocGia;
            gioHang.docGia = docGia;
            gioHang.getSachs().add(sach);
            gioHang.daThanhToan = false;
            gioHangRepository.save(gioHang);
            return gioHang;
        } else {
            int maDocGia = docGia.maDocGia;
            GioHang gioHang = gioHangRepository.findGioHangByMaDocGia(maDocGia);
            double tongTienSach = gioHang.tongTien;
            double giaSach = sach.giaTien;
            tongTienSach += giaSach;
            gioHang.tongTien = tongTienSach;
            gioHang.getSachs().add(sach);
            gioHang.daThanhToan = false;
            gioHangRepository.save(gioHang);
            return gioHang;
        }
    }

    public Boolean isGioHangExist(int maTk) {
        DocGia docGia  = docGiaRepository.findByMaTk(maTk);
        int maGioHang = docGia.maDocGia;
        GioHang gioHang = gioHangRepository.findGioHangByMaDocGia(maGioHang);
        if (gioHang == null) {
            return false;
        }
        return true;
    }


    @Override
    public LayGioHangDto layGioHangTheoMaTk(int maTk) {
        GioHang gioHang = gioHangRepository.findGioHangByMaTK(maTk);
        List<Sach> sachs = sachRepository.findSachTrongGioHangByMaTk(maTk);
        LayGioHangDto layGioHangDto = new LayGioHangDto();
        layGioHangDto.maGioHang = gioHang.maGioHang;
        layGioHangDto.tongTien = gioHang.tongTien;
        layGioHangDto.daThanhToan = gioHang.daThanhToan;
        layGioHangDto.maDocGia = gioHang.docGia.maDocGia;
        layGioHangDto.tenDocGia = gioHang.docGia.ten;
        layGioHangDto.diaChi = gioHang.docGia.diaCHi;
        layGioHangDto.maTaiKhoan = gioHang.docGia.taiKhoan.maTk;
        for (Sach element : sachs) {
            GetChiTietSachDto getChiTietSachDto = new GetChiTietSachDto();
            getChiTietSachDto.maSach = element.maSach;
            getChiTietSachDto.tenSach = element.tenSach;
            getChiTietSachDto.giaTien = element.giaTien;
            layGioHangDto.sachList.add(getChiTietSachDto);
        }
        return layGioHangDto;
    }

    @Transactional
    @Override
    public void xoaSachKhoiGioHang(int maSach, int maTk) {
        GioHang gioHang = gioHangRepository.findGioHangByMaTK(maTk);
        Sach sach = sachRepository.timSachTheoId(maSach);
        gioHang.tongTien = gioHang.tongTien - sach.giaTien;
        gioHangRepository.save(gioHang);
        gioHangRepository.removeSachFromGioHangByMaSach(maSach, maTk);
    }

    @Transactional
    @Override
    public void xoaGioHangByMaTk(int maTk) {
        DocGia docGia = docGiaRepository.findByMaTk(maTk);
        gioHangRepository.deleteByMaDocGia(docGia.maDocGia);
    }

}
