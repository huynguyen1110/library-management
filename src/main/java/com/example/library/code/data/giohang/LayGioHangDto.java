package com.example.library.code.data.giohang;

import com.example.library.code.data.sach.GetChiTietSachDto;
import com.example.library.code.models.entities.Sach;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LayGioHangDto {

    public Integer maGioHang;
    public Double tongTien;
    public Boolean daThanhToan;
    public Integer maDocGia;
    public String tenDocGia;
    public String diaChi;
    public Integer maTaiKhoan;
    public Integer tenTaiKhoang;
    public List<GetChiTietSachDto> sachList = new ArrayList<>();
}
