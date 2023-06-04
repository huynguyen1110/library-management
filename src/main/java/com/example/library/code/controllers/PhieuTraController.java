package com.example.library.code.controllers;

import com.example.library.code.data.phieuTra.CapNhapSachTraDto;
import com.example.library.code.data.sach.GetChiTietSachDto;
import com.example.library.code.data.sach.GetChiTietSachPhieuMuon;
import com.example.library.code.models.entities.PhieuMuon;
import com.example.library.code.models.entities.PhieuTra;
import com.example.library.code.models.entities.Sach;
import com.example.library.code.services.serviceimp.PhieuMuonService;
import com.example.library.code.services.serviceimp.PhieuTraService;
import com.example.library.code.services.serviceimp.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class PhieuTraController {

    @Autowired
    private PhieuTraService phieuTraService;

    @Autowired
    private PhieuMuonService phieuMuonService;

    @Autowired
    private SachService sachService;

    @PostMapping("admin/tra-sach")
    public ResponseEntity<CapNhapSachTraDto> handleAjaxRequest(@RequestBody CapNhapSachTraDto sachTraDto) {
        PhieuTra phieuTra = phieuTraService.timPhieuTraId(sachTraDto.maPhieuTra);
        for(int i = 0; i < sachTraDto.idSachs.length; i++){
            Sach sach = sachService.timSachTheoId2(sachTraDto.idSachs[i]);
            sach.setSoLuong(sach.getSoLuong()+1);
            phieuTra.getSachs().add(sach);
        }
        phieuTra.setSachs(phieuTra.getSachs());
        phieuTraService.taoPhieuTra(phieuTra);

        PhieuMuon phieuMuon = phieuMuonService.timPhieuMuonId(sachTraDto.maPhieuMuon);
        if(phieuMuon.getSachs().size() == phieuTra.getSachs().size()){
            phieuTra.setTrang_thai(true);
        }
        phieuTraService.taoPhieuTra(phieuTra);
        return ResponseEntity.ok().body(sachTraDto);
    }
}
