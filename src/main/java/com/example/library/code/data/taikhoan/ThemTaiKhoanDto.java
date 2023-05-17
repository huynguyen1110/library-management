package com.example.library.code.data.taikhoan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ThemTaiKhoanDto {
    public Integer maTk;
    public String tenTk;
    public String matKhau;
    public String nhapLaiMk;
}
