package com.example.library.code.services.serviceimp;

import com.example.library.code.models.entities.NhaXuatBan;
import com.example.library.code.repositories.NhaXuatBanRepository;
import com.example.library.code.services.iservices.INhaXuatBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhaXuatBanService implements INhaXuatBanService {

    @Autowired
    private NhaXuatBanRepository nhaXuatBanRepository;
    @Override
    public List<NhaXuatBan> danhSachNhaXuatBan() {
        return nhaXuatBanRepository.findAll();
    }

    @Override
    public NhaXuatBan timNhaXuatBanTheoMa(int id) {
        return nhaXuatBanRepository.timNhaXuatBanTheoMa(id);
    }
}
