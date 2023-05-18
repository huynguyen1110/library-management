package com.example.library.code.controllers;

import com.example.library.code.data.giohang.LayGioHangDto;
import com.example.library.code.models.entities.GioHang;
import com.example.library.code.services.serviceimp.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class GioHangController {

    @Autowired
    private GioHangService gioHangService;

//    Fe cần chuyền vào mã sách và mã tk
    @PostMapping("them-vao-gio-hang")
    public GioHang themGioHang(@RequestParam int maSach, @RequestParam int maTk) {
        return gioHangService.themSachVaoGioHang(maSach, maTk);
    }

    @GetMapping("lay-gio-hang")
    public LayGioHangDto layGioHangTheoMaTk(@RequestParam("maTk") int maTk) {
        return gioHangService.layGioHangTheoMaTk(maTk);
    }

    @DeleteMapping("xoa-sach-khoi-gio-hang")
    public void xoaSachKhoiGioHang(@RequestParam int maSach, @RequestParam int maTk) {
        gioHangService.xoaSachKhoiGioHang(maSach, maTk);
    }
}
