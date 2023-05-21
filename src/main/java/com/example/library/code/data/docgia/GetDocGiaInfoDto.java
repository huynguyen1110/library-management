package com.example.library.code.data.docgia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class GetDocGiaInfoDto {

    public String ten;
    public Date ngaySinh;
    public String sdt;
    public String email;
    public String diaCHi;
}
