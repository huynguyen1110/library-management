package com.example.library.code.controllers;

import com.example.library.code.models.entities.Sach;
import com.example.library.code.services.iservices.ISachService;
import com.example.library.code.services.serviceimp.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/v1/")
public class SachController {

    @Autowired
    private SachService SachService;

    @GetMapping("sach-moi")
    public List<Sach> sachMoi() {
        LocalDate ngayKetThuc = LocalDate.now();
        LocalDate ngayBatDau = ngayKetThuc.minusDays(10);
        return  SachService.timSachMoi(ngayBatDau, ngayKetThuc);
    }
}
