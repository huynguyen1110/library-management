package com.example.library.code.controllers;

import com.example.library.code.data.docgia.GetDocGiaInfoDto;
import com.example.library.code.data.docgia.UpdateDocGiaDto;
import com.example.library.code.models.entities.DocGia;
import com.example.library.code.services.serviceimp.DocGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DocGiaController {

    @Autowired
    private DocGiaService docGiaService;

    @GetMapping("info-update")
    public ModelAndView capNhatThongTinPage() {
        ModelAndView modelAndView = new ModelAndView("/user/user-info-update");
        return modelAndView;
    }

    @GetMapping("admin/info-update")
    public ModelAndView adminCapNhatThongTinPage() {
        ModelAndView modelAndView = new ModelAndView("/members/update-members");
        return modelAndView;
    }

    @PutMapping("cap-nhat-thong-tin")
    public UpdateDocGiaDto capNhatThongTin(@RequestBody UpdateDocGiaDto updateDocGiaDto, @RequestParam int maTk) {
        return docGiaService.capNhatThongTinDocGia(updateDocGiaDto, maTk);
    }

    @GetMapping("lay-thong-tin")
    public GetDocGiaInfoDto layThongTin(@RequestParam int maTk) {
        return docGiaService.layThongTinDocGia(maTk);
    }

    @GetMapping("admin/docgia-danh-sach")
    public ModelAndView listOfMemberPage() {
        ModelAndView modelAndView= new ModelAndView("/members/listMember");
        return modelAndView;
    }

    @GetMapping("admin/find-all-doc-gia")
    public List<GetDocGiaInfoDto> getDocGiaInfoDtoList() {
        return docGiaService.getDocGiaInfoDtos();
    }

    @PutMapping("admin/xoa-doc-gia")
    public DocGia xoaDocGiaByMaTk(@RequestParam int maTk) {
        return docGiaService.xoaDocGiaByMaTk(maTk);
    }
}
