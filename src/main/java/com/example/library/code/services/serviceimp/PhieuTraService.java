package com.example.library.code.services.serviceimp;

import com.example.library.code.models.entities.PhieuMuon;
import com.example.library.code.models.entities.PhieuTra;
import com.example.library.code.repositories.PhieuTraRepository;
import com.example.library.code.services.iservices.IPhieuTraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuTraService implements IPhieuTraService {

    @Autowired
    private PhieuTraRepository phieuTraRepository;
    @Override
    public PhieuTra taoPhieuTra(PhieuTra phieuTra) {
        phieuTraRepository.save(phieuTra);
        return phieuTra;
    }

    @Override
    public List<PhieuTra> danhSachPhieuTra() {
        return phieuTraRepository.findAll();
    }

    @Override
    public PhieuTra timPhieuTraId(int id) {
        return phieuTraRepository.findById(id).get();
    }
}
