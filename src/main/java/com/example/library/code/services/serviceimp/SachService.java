package com.example.library.code.services.serviceimp;

import com.example.library.code.models.entities.Sach;
import com.example.library.code.repositories.SachRepository;
import com.example.library.code.services.iservices.ISachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SachService implements ISachService {

    @Autowired
    private SachRepository sachRepository;

    @Override
    public List<Sach> timSachMoi(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        return sachRepository.timSachMoi(ngayBatDau, ngayKetThuc);
    }
}
