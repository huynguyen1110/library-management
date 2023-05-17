package com.example.library.code.controllers;

import com.example.library.code.data.taikhoan.LoginTaiKhoanDto;
import com.example.library.code.data.taikhoan.ThemTaiKhoanDto;
import com.example.library.code.models.entities.TaiKhoan;
import com.example.library.code.services.serviceimp.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class LoginAndRegisterController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @PostMapping("them-tai-khoan")
    public ThemTaiKhoanDto themTaiKhoan(@RequestBody ThemTaiKhoanDto themTaiKhoanDto) {
        return taiKhoanService.taoTaiKhoan(themTaiKhoanDto);
    }

    @PostMapping("dang-nhap")
    public TaiKhoan dangNhapTaiKhoan(@RequestBody LoginTaiKhoanDto loginTaiKhoanDto) {
        return taiKhoanService.loginTaiKhoan(loginTaiKhoanDto);
    }
}
