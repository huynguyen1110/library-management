package com.example.library.code.services.iservices;

import com.example.library.code.models.entities.DocGia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDocGiaService {
    List<DocGia> timTatCa();

    DocGia timTheoMa(int id);
}
