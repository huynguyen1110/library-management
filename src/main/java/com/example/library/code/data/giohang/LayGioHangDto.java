package com.example.library.code.data.giohang;

import com.example.library.code.models.entities.Sach;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public class LayGioHangDto {

    public Integer maGioHang;
    public Double tongTien;
    public Boolean daThanhToan;
    public Integer maDocGia;
    public String tenDocGia;
    public String diaChi;
    public Integer maTaiKhoan;
    public Integer tenTaiKhoang;
    public List<Sach> sachList;
}
