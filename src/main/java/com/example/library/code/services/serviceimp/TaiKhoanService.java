package com.example.library.code.services.serviceimp;

import com.example.library.code.data.taikhoan.LoginTaiKhoanDto;
import com.example.library.code.data.taikhoan.ThemTaiKhoanDto;
import com.example.library.code.models.entities.DocGia;
import com.example.library.code.models.entities.TaiKhoan;
import com.example.library.code.models.enums.Role;
import com.example.library.code.repositories.DocGiaRepository;
import com.example.library.code.repositories.GioHangRepository;
import com.example.library.code.repositories.TaiKhoanRepository;
import com.example.library.code.services.iservices.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TaiKhoanService implements ITaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private DocGiaRepository docGiaRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public ThemTaiKhoanDto taoTaiKhoan(@RequestBody ThemTaiKhoanDto themTaiKhoanDto) {
        TaiKhoan timKiemTaiKhoan = taiKhoanRepository.findByTenTk(themTaiKhoanDto.tenTk);
        if (themTaiKhoanDto != null && themTaiKhoanDto.matKhau.equals(themTaiKhoanDto.nhapLaiMk) && timKiemTaiKhoan == null) {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.tenTk = themTaiKhoanDto.tenTk;
            taiKhoan.matKhau = themTaiKhoanDto.matKhau;
            taiKhoan.role = Role.NGUOI_DUNG;
            taiKhoanRepository.save(taiKhoan);

            DocGia docGia = new DocGia();
            docGia.ten = taiKhoan.tenTk;
            docGia.taiKhoan = timKiemTaiKhoan;
            docGiaRepository.save(docGia);

            return themTaiKhoanDto;
        }else {
            return null;
        }
    }

    @Override
    public TaiKhoan loginTaiKhoan(LoginTaiKhoanDto loginTaiKhoanDto) {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenTk(loginTaiKhoanDto.tenTk);
        String tenTk = taiKhoan.tenTk;
        DocGia docGia = docGiaRepository.findByTen(tenTk);
        if (docGia != null) {
            docGia.taiKhoan = taiKhoan;
            docGiaRepository.save(docGia);
        }
        if (taiKhoan != null && taiKhoan.matKhau.equals(loginTaiKhoanDto.matKhau)) {
            return taiKhoan;
        }
        return null;
    }
}
