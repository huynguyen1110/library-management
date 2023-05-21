package com.example.library.code.services.serviceimp;

import com.example.library.code.models.entities.DocGia;
import com.example.library.code.repositories.DocGiaRepository;
import com.example.library.code.services.iservices.IDocGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocGiaService implements IDocGiaService {
    @Autowired
    private DocGiaRepository docGiaRepository;

    @Override
    public List<DocGia> timTatCa() {
        return docGiaRepository.findAll();
    }

    @Override
    public DocGia timTheoMa(int id) {
        return docGiaRepository.findById(id).get();
    }
}
