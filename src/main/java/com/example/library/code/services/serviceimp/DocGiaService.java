package com.example.library.code.services.serviceimp;

import com.example.library.code.data.docgia.GetDocGiaInfoDto;
import com.example.library.code.data.docgia.UpdateDocGiaDto;
import com.example.library.code.models.entities.DocGia;
import com.example.library.code.models.entities.TaiKhoan;
import com.example.library.code.repositories.DocGiaRepository;
import com.example.library.code.repositories.TaiKhoanRepository;
import com.example.library.code.services.iservices.IDocGiaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DocGiaService implements IDocGiaService {
    @Autowired
    private DocGiaRepository docGiaRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private final ModelMapper mapper;

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

    @Override
    public List<DocGia> timTatCa() {
        return docGiaRepository.findAll();
    }


    @Override
    public DocGia timTheoMa(int id) {
        return docGiaRepository.findById(id).get();
    }

    @Override
    public List<GetDocGiaInfoDto> getDocGiaInfoDtos() {
        List<DocGia> docGias = docGiaRepository.findAll();
        List<GetDocGiaInfoDto> docGiaInfoDtos = docGias.stream()
                .map(docGia -> mapper.map(docGia, GetDocGiaInfoDto.class))
                .collect(Collectors.toList());
        return docGiaInfoDtos;
    }

    @Override
    public DocGia xoaDocGiaByMaTk(int maTk) {
        DocGia docGia = docGiaRepository.findByMaTk(maTk);
        TaiKhoan taiKhoan = taiKhoanRepository.findByMaTk(maTk);
        docGia.daXoa = true;
        taiKhoan.daXoa = true;
        taiKhoan.matKhau = "deleted tai khoan ";
        docGiaRepository.save(docGia);
        taiKhoanRepository.save(taiKhoan);
        return docGia;
    }
}
