package com.example.library.code.controllers;

import com.example.library.code.data.theloai.CapNhatTheLoaiDto;
import com.example.library.code.data.theloai.ThemTheLoaiDto;
import com.example.library.code.models.entities.TheLoai;
import com.example.library.code.services.serviceimp.TheLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class TheLoaiController {

    @Autowired
    private TheLoaiService theLoaiService;

    @PostMapping("admin/them-the-loai")
    public ThemTheLoaiDto themTheLoai(@RequestBody ThemTheLoaiDto themTheLoaiDto) {
        return theLoaiService.themTheLoai(themTheLoaiDto);
    }

    @GetMapping("admin/danh-sach-the-loai")
    public List<TheLoai> danhSachTheLoai() {
        return theLoaiService.danhSachTheLoai();
    }

    @PutMapping("admin/cap-nhat-the-loai")
    public CapNhatTheLoaiDto danhSachTheLoai(@RequestBody CapNhatTheLoaiDto capNhatTheLoaiDto, @RequestParam int maTheLoai) {
        return theLoaiService.capNhatTheLoaiDto(capNhatTheLoaiDto, maTheLoai);
    }

    @PutMapping("admin/xoa-the-loai")
    public TheLoai xoaTheLoai(@RequestParam int maTheLoai) {
        return theLoaiService.xoaTheLoaiById(maTheLoai);
    }
}
