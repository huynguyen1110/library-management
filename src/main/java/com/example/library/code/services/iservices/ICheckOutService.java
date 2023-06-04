package com.example.library.code.services.iservices;

import com.example.library.code.data.phieumuontra.LayPhieuMuonTraDto;
import com.example.library.code.models.entities.PhieuMuon;
import com.example.library.code.models.entities.PhieuTra;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ICheckOutService {

    PhieuMuon taoPhieuMuon(int maTk);

    PhieuTra taoPhieuTra(int maTk);

    List<LayPhieuMuonTraDto> layPhieuMuonTraInfo(int maTk);
}
