package com.example.library.code.services.serviceimp;

import com.example.library.code.models.entities.TheLoai;
import com.example.library.code.repositories.TheLoaiRepository;
import com.example.library.code.services.iservices.ITheLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TheLoaiService implements ITheLoaiService {
    @Autowired
    private TheLoaiRepository theLoaiRepository;

    @Override
    public List<TheLoai> danhSachTheLoai() {
        return theLoaiRepository.findAll();
    }

    @Override
    public TheLoai timTheLoaiTheoMa(int id) {
        return theLoaiRepository.timTheLoaiTheoMa(id);
    }
}
