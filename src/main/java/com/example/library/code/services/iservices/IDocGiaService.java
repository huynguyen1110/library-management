package com.example.library.code.services.iservices;

import com.example.library.code.data.docgia.GetDocGiaInfoDto;
import com.example.library.code.data.docgia.UpdateDocGiaDto;

public interface IDocGiaService {

    UpdateDocGiaDto capNhatThongTinDocGia(UpdateDocGiaDto updateDocGiaDto, int maTk);

    GetDocGiaInfoDto layThongTinDocGia(int maTk);
}
