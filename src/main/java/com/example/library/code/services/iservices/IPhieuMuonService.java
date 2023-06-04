package com.example.library.code.services.iservices;

import com.example.library.code.models.entities.PhieuMuon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPhieuMuonService {
    PhieuMuon taoPhieuMuon(PhieuMuon phieuMuon);

    List<PhieuMuon> danhSachPhieuMuon();

    PhieuMuon timPhieuMuonId(int id);

}
