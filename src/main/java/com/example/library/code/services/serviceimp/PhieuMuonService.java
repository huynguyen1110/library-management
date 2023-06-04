package com.example.library.code.services.serviceimp;

import com.example.library.code.data.phieuMuon.CapNhapSachMuonDto;
import com.example.library.code.models.entities.PhieuMuon;
import com.example.library.code.repositories.PhieuMuonRepository;
import com.example.library.code.services.iservices.IPhieuMuonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuMuonService implements IPhieuMuonService {

    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Override
    public PhieuMuon taoPhieuMuon(PhieuMuon phieuMuon) {
        phieuMuonRepository.save(phieuMuon);
        return phieuMuon;
    }

//    @Override
//    public List<PhieuMuon> danhSachPhieuMuon() {
//        return phieuMuonRepository.findAll();
//    }

    @Override
    public List<PhieuMuon> danhSachPhieuMuon() {
        return phieuMuonRepository.findAll();
    }

    @Override
    public PhieuMuon timPhieuMuonId(int id) {
        return phieuMuonRepository.findById(id).get();
    }

}
