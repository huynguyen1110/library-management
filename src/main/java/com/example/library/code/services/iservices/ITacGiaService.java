package com.example.library.code.services.iservices;

import com.example.library.code.models.entities.TacGia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITacGiaService {
    List<TacGia> danhSachTacGia();

    TacGia timTacGiaTheoMa(int id);
}
