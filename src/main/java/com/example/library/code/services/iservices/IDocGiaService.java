package com.example.library.code.services.iservices;

import com.example.library.code.data.docgia.GetDocGiaInfoDto;
import com.example.library.code.data.docgia.UpdateDocGiaDto;
import com.example.library.code.models.entities.DocGia;

import java.util.List;

public interface IDocGiaService {

    UpdateDocGiaDto capNhatThongTinDocGia(UpdateDocGiaDto updateDocGiaDto, int maTk);

    GetDocGiaInfoDto layThongTinDocGia(int maTk);

    List<DocGia> timTatCa();

    DocGia timTheoMa(int id);

    List<GetDocGiaInfoDto> getDocGiaInfoDtos();

    DocGia xoaDocGiaByMaTk(int maTk);
}
