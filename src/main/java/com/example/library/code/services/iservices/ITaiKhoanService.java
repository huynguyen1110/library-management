package com.example.library.code.services.iservices;

import com.example.library.code.data.taikhoan.LoginTaiKhoanDto;
import com.example.library.code.data.taikhoan.ThemTaiKhoanDto;
import com.example.library.code.models.entities.TaiKhoan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ITaiKhoanService {

    ThemTaiKhoanDto taoTaiKhoan(ThemTaiKhoanDto themTaiKhoanDto);

    TaiKhoan loginTaiKhoan(LoginTaiKhoanDto loginTaiKhoanDto);
}
