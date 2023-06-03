package com.example.library.code.services.serviceimp;

import com.example.library.code.data.giohang.LayGioHangDto;
import com.example.library.code.data.phieumuontra.LayPhieuMuonTraDto;
import com.example.library.code.data.sach.GetChiTietSachDto;
import com.example.library.code.models.entities.DocGia;
import com.example.library.code.models.entities.PhieuMuon;
import com.example.library.code.models.entities.PhieuTra;
import com.example.library.code.models.entities.Sach;
import com.example.library.code.models.enums.HinhThucMuonTra;
import com.example.library.code.repositories.*;
import com.example.library.code.services.iservices.ICheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CheckOutService implements ICheckOutService {

    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Autowired
    private PhieuTraRepository phieuTraRepository;

    @Autowired
    private DocGiaRepository docGiaRepository;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private SachRepository sachRepository;

    @Override
    public PhieuMuon taoPhieuMuon(int maTk) {
        DocGia docGia = docGiaRepository.findByMaTk(maTk);
        LayGioHangDto layGioHangDto = gioHangService.layGioHangTheoMaTk(maTk);
        PhieuMuon phieuMuon = new PhieuMuon();
        phieuMuon.ngayMuon = LocalDateTime.now();
        phieuMuon.tongTien = layGioHangDto.tongTien;
        phieuMuon.soLuong = layGioHangDto.sachList.size();
        phieuMuon.trang_thai = true;
        phieuMuon.hinhThucMuonTra = HinhThucMuonTra.ONLINE;
        phieuMuon.docGia = docGia;
        for (GetChiTietSachDto sach : layGioHangDto.sachList) {
            Sach sachTrongGioHang = sachRepository.timSachTheoId(sach.maSach);
            phieuMuon.getSachs().add(sachTrongGioHang);
        }
        phieuMuonRepository.save(phieuMuon);
        taoPhieuTra(maTk);
        return phieuMuon;
    }

    @Override
    public PhieuTra taoPhieuTra(int maTk) {
        DocGia docGia = docGiaRepository.findByMaTk(maTk);
        LayGioHangDto layGioHangDto = gioHangService.layGioHangTheoMaTk(maTk);
        PhieuTra phieuTra = new PhieuTra();
        phieuTra.ngayTra = LocalDateTime.now().plusDays(14) ;
        phieuTra.tongTien = layGioHangDto.tongTien;
        phieuTra.soLuong = 0;
        phieuTra.trang_thai = false;
        phieuTra.docGia = docGia;
        for (GetChiTietSachDto sach : layGioHangDto.sachList) {
            gioHangService.xoaSachKhoiGioHangKhiThanhToan(sach.maSach, maTk);
        }
        phieuTraRepository.save(phieuTra);
        gioHangService.xoaGioHangByMaTk(maTk);
        return phieuTra;
    }

    @Override
    public List<LayPhieuMuonTraDto> layPhieuMuonTraInfo(int maTk) {
        DocGia docGia = docGiaRepository.findByMaTk(maTk);
        List<PhieuMuon> phieuMuonList = phieuMuonRepository.findByMaDocGiaWithSach(docGia.maDocGia);
        List<PhieuTra> phieuTraList = phieuTraRepository.findByMaDocGiaWithSach(docGia.maDocGia);
        List<LayPhieuMuonTraDto> phieuMuonTraDtos = new ArrayList<>();
        for (int phieuMuonIndex = 0; phieuMuonIndex < phieuMuonList.size(); phieuMuonIndex++) {
            LayPhieuMuonTraDto layPhieuMuonTraDto = new LayPhieuMuonTraDto();
            layPhieuMuonTraDto.maPhieuMuon = phieuMuonList.get(phieuMuonIndex).maPhieuMuon;
            layPhieuMuonTraDto.ngayMuon = phieuMuonList.get(phieuMuonIndex).ngayMuon;
            layPhieuMuonTraDto.soLuong = phieuMuonList.get(phieuMuonIndex).soLuong;
            layPhieuMuonTraDto.tongTien = phieuMuonList.get(phieuMuonIndex).tongTien;
            layPhieuMuonTraDto.docGia = docGia;
            if (phieuMuonList.get(phieuMuonIndex).trang_thai == true) {
                layPhieuMuonTraDto.trangThaiMuon = "Đã Xác Nhận";
            } else {
                layPhieuMuonTraDto.trangThaiMuon = "Chờ Xác nhận";
            }
            layPhieuMuonTraDto.setSachMuon(phieuMuonList.get(phieuMuonIndex).getSachs());

            layPhieuMuonTraDto.maPhieuTra = phieuTraList.get(phieuMuonIndex).maPhieuTra;
            layPhieuMuonTraDto.ngayTra = phieuTraList.get(phieuMuonIndex).ngayTra;
            layPhieuMuonTraDto.soLuongTra = phieuTraList.get(phieuMuonIndex).soLuong;
            layPhieuMuonTraDto.setSachTra(phieuTraList.get(phieuMuonIndex).getSachs());
            if (phieuTraList.get(phieuMuonIndex).trang_thai == true) {
                layPhieuMuonTraDto.trangThaiTra = "Đã Xác Nhận";
            } else {
                layPhieuMuonTraDto.trangThaiTra = "Chờ Xác nhận";
            }


            phieuMuonTraDtos.add(layPhieuMuonTraDto);
        }

        return phieuMuonTraDtos;
    }
}
