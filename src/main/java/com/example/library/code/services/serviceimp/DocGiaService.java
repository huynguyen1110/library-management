package com.example.library.code.services.serviceimp;

import com.example.library.code.data.docgia.GetDocGiaInfoDto;
import com.example.library.code.data.docgia.UpdateDocGiaDto;
import com.example.library.code.models.entities.DocGia;
import com.example.library.code.repositories.DocGiaRepository;
import com.example.library.code.services.iservices.IDocGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocGiaService implements IDocGiaService {

    @Autowired
    private DocGiaRepository docGiaRepository;

    @Override
    public UpdateDocGiaDto capNhatThongTinDocGia(UpdateDocGiaDto updateDocGiaDto, int maTk) {
        DocGia docGia = docGiaRepository.findByMaTk(maTk);
        docGia.ten = updateDocGiaDto.ten;
        docGia.ngaySinh = updateDocGiaDto.ngaySinh;
        docGia.sdt = updateDocGiaDto.sdt;
        docGia.email = updateDocGiaDto.email;
        docGia.diaCHi = updateDocGiaDto.diaChi;
        docGiaRepository.save(docGia);
        return updateDocGiaDto;
    }

    @Override
    public GetDocGiaInfoDto layThongTinDocGia(int maTk) {
        DocGia docGia = docGiaRepository.findByMaTk(maTk);
        GetDocGiaInfoDto getDocGiaInfoDto = new GetDocGiaInfoDto();
        getDocGiaInfoDto.ten = docGia.ten;
        getDocGiaInfoDto.ngaySinh = docGia.ngaySinh;
        getDocGiaInfoDto.sdt = docGia.sdt;
        getDocGiaInfoDto.email = docGia.email;
        getDocGiaInfoDto.diaCHi = docGia.diaCHi;
        return getDocGiaInfoDto;
    }
}
