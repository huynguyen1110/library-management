package com.example.library.code.services.serviceimp;

import com.example.library.code.models.entities.TacGia;
import com.example.library.code.repositories.TacGiaRepository;
import com.example.library.code.services.iservices.ITacGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacGiaService implements ITacGiaService {
    @Autowired
    private TacGiaRepository tacGiaRepository;
    @Override
    public List<TacGia> danhSachTacGia() {
        return tacGiaRepository.findAll();
    }

    @Override
    public TacGia timTacGiaTheoMa(int id) {
        return tacGiaRepository.timTacGiaTheoMa(id);
    }
}
