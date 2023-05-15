package com.example.library.code.controllers;

import com.example.library.code.data.GetChiTietSachDto;
import com.example.library.code.models.entities.Sach;
import com.example.library.code.services.serviceimp.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class SachController {

    @Autowired
    private SachService sachService;

    @GetMapping("sach-moi")
    public List<Sach> sachMoi() {
        LocalDate ngayKetThuc = LocalDate.now();
        LocalDate ngayBatDau = ngayKetThuc.minusDays(10);
        return  sachService.timSachMoi(ngayBatDau);
    }

    @GetMapping("tat-ca")
    public List<Sach> tatCaSach() {
        return sachService.timTatCa();
    }

    @GetMapping("sach")
    public List<Sach> sachTheoTheLoai(@RequestParam("the-loai") String theLoai) {
        return sachService.timTheLoaiTruyenNgan(theLoai);
    }

    @GetMapping("chi-tiet-sach")
    public GetChiTietSachDto timSachTheoId(@RequestParam("id") int id) {
            return sachService.timSachTheoId(id);
    }
}
