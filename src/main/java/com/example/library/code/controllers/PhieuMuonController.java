package com.example.library.code.controllers;

import com.example.library.code.data.phieuMuon.CapNhapSachMuonDto;
import com.example.library.code.data.phieuMuon.TaoPhieuMuonDto;
import com.example.library.code.data.sach.GetChiTietSachDto;
import com.example.library.code.data.sach.GetChiTietSachPhieuMuon;
import com.example.library.code.models.entities.*;
import com.example.library.code.models.enums.HinhThucMuonTra;
import com.example.library.code.models.enums.HinhThucThanhToan;
import com.example.library.code.services.serviceimp.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PhieuMuonController {

    @Autowired
    private SachService sachService;

    @Autowired
    private TheLoaiService theLoaiService;

    @Autowired
    private DocGiaService docGiaService;

    @Autowired
    private PhieuMuonService phieuMuonService;

    @Autowired
    private PhieuTraService phieuTraService;

    @GetMapping("admin/phieu-muon")
    public ModelAndView formPhieuMuon(){
        ModelAndView modelAndView = new ModelAndView("issue/issueNew");
        List<TheLoai> theLoais = theLoaiService.danhSachTheLoai();
        List<Sach> sachs = sachService.adminTimTatCaSach();
        List<DocGia> docGias = docGiaService.timTatCa();
        modelAndView.addObject("sachs", sachs);
        modelAndView.addObject("theLoais", theLoais);
        modelAndView.addObject("docGias", docGias);
        return modelAndView;
    }

    @PostMapping("admin/phieu-muon")
    public ResponseEntity<TaoPhieuMuonDto> taoPhieuMuon(@RequestBody TaoPhieuMuonDto phieuMuonDto){
        PhieuMuon phieuMuon = new PhieuMuon();
//        phieuMuon.setMaPhieuMuon(phieuMuonService.danhSachPhieuMuon().size()+1);
        phieuMuon.setHinhThucThanhToan(HinhThucThanhToan.TIEN_MAT);
        phieuMuon.setHinhThucMuonTra(HinhThucMuonTra.OFFLINE);
        phieuMuon.setDocGia(docGiaService.timTheoMa(phieuMuonDto.idDocGia));
        phieuMuon.setSoLuong(phieuMuonDto.idSachs.length);
        phieuMuon.setNgayMuon(LocalDateTime.now());
        phieuMuon.setTongTien(sachService.tinhTien(phieuMuonDto.idSachs));
        phieuMuon.setTrang_thai(true);
        for(int i = 0; i < phieuMuonDto.idSachs.length; i++){
            Sach sach = sachService.timSachTheoId2(phieuMuonDto.idSachs[i]);
            sach.setSoLuong(sach.getSoLuong()-1);
            phieuMuon.getSachs().add(sach);
        }
        phieuMuon.setSachs(phieuMuon.getSachs());
        phieuMuonService.taoPhieuMuon(phieuMuon);

        PhieuTra phieuTra = new PhieuTra();
        phieuTra.setHinhThucThanhToan(HinhThucThanhToan.TIEN_MAT);
        phieuTra.setHinhThucTra(HinhThucMuonTra.OFFLINE);
        phieuTra.setDocGia(docGiaService.timTheoMa(phieuMuonDto.idDocGia));
        phieuTra.setSoLuong(phieuMuonDto.idSachs.length);
        phieuTra.setNgayTra(LocalDateTime.parse(phieuMuonDto.ngayTraDuKien.concat("T").concat(LocalTime.now().toString())));
        phieuTra.setTongTien(sachService.tinhTien(phieuMuonDto.idSachs));
        phieuTra.setTrang_thai(false);

        phieuTraService.taoPhieuTra(phieuTra);

        return ResponseEntity.ok().body(phieuMuonDto);
    }

    @GetMapping("admin/phieu-muon/danh-sach")
    public ModelAndView danhSachPhieuMuon(){
        ModelAndView modelAndView = new ModelAndView("issue/listIssue");
        List<PhieuMuon> issues = phieuMuonService.danhSachPhieuMuon();
        List<PhieuTra> issuesRes = phieuTraService.danhSachPhieuTra();
        modelAndView.addObject("issues", issues);
        modelAndView.addObject("issuesRes", issuesRes);
        return modelAndView;
    }

    @PostMapping("admin/phieu-muon/them-sach")
    public ResponseEntity<GetChiTietSachPhieuMuon> handleAjaxRequest(@RequestBody int id) {

        GetChiTietSachDto sach = sachService.timSachTheoId(id);
        GetChiTietSachPhieuMuon sachDto = new GetChiTietSachPhieuMuon();
        sachDto.setMaSach(sach.maSach);
        sachDto.setTenSach(sach.tenSach);
        sachDto.setTacGia(sach.tacGia);
        if(sach.getSoLuong() > 0){
            return ResponseEntity.ok().body(sachDto);
        }
        return ResponseEntity.badRequest().body(sachDto);

    }

}
