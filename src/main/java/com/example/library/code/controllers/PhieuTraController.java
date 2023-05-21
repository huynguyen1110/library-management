package com.example.library.code.controllers;

import com.example.library.code.data.phieuTra.CapNhapSachTraDto;
import com.example.library.code.data.sach.GetChiTietSachDto;
import com.example.library.code.data.sach.GetChiTietSachPhieuMuon;
import com.example.library.code.models.entities.PhieuTra;
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
    private SachService sachService;

    @PostMapping("admin/tra-sach")
    public ResponseEntity<CapNhapSachTraDto> handleAjaxRequest(@RequestBody CapNhapSachTraDto sachTraDto) {
        PhieuTra phieuTra = phieuTraService.timPhieuTraId(sachTraDto.maPhieuTra);
        for(int i = 0; i < sachTraDto.idSachs.length; i++){
            phieuTra.getSachs().add(sachService.timSachTheoId2(sachTraDto.idSachs[i]));
        }
        phieuTra.setSachs(phieuTra.getSachs());
        phieuTraService.taoPhieuTra(phieuTra);

        return ResponseEntity.ok().body(sachTraDto);
    }
}
