package com.example.library.code.services.iservices;

import com.example.library.code.models.entities.PhieuTra;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPhieuTraService {
    PhieuTra taoPhieuTra(PhieuTra phieuTra);

    List<PhieuTra> danhSachPhieuTra();

    PhieuTra timPhieuTraId(int id);


}
