package com.example.library.code.services.iservices;

import com.example.library.code.models.entities.NhaXuatBan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INhaXuatBanService {

    List<NhaXuatBan> danhSachNhaXuatBan();

    NhaXuatBan timNhaXuatBanTheoMa(int id);
}
