package com.example.library.code.services.serviceimp;

import com.example.library.code.data.theloai.CapNhatTheLoaiDto;
import com.example.library.code.data.theloai.ThemTheLoaiDto;
import com.example.library.code.models.entities.TheLoai;
import com.example.library.code.repositories.TheLoaiRepository;
import com.example.library.code.services.iservices.ITheLoaiService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TheLoaiService implements ITheLoaiService {
    @Autowired
    private TheLoaiRepository theLoaiRepository;

    @Autowired
    private final ModelMapper mapper;

    @Override
    public List<TheLoai> danhSachTheLoai() {
        return theLoaiRepository.findAll();
    }

    @Override
    public TheLoai timTheLoaiTheoMa(int id) {
        return theLoaiRepository.timTheLoaiTheoMa(id);
    }

    @Override
    public ThemTheLoaiDto themTheLoai(ThemTheLoaiDto themTheLoaiDto) {
        TheLoai theLoai = mapper.map(themTheLoaiDto, TheLoai.class);
        theLoaiRepository.save(theLoai);
        return themTheLoaiDto;
    }

    @Override
    public CapNhatTheLoaiDto capNhatTheLoaiDto(CapNhatTheLoaiDto capNhatTheLoaiDto, int id) {
        TheLoai theLoai = theLoaiRepository.timTheLoaiTheoMa(id);
        theLoai.tenTheLoai = capNhatTheLoaiDto.tenTheLoai;
        theLoaiRepository.save(theLoai);
        return capNhatTheLoaiDto;
    }

    @Override
    public TheLoai xoaTheLoaiById(int id) {
        TheLoai theLoai = theLoaiRepository.timTheLoaiTheoMa(id);
        theLoai.daXoa = true;
        theLoaiRepository.save(theLoai);
        return theLoai;
    }
}
