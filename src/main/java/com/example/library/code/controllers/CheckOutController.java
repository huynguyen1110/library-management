package com.example.library.code.controllers;

import com.example.library.code.data.phieumuontra.LayPhieuMuonTraDto;
import com.example.library.code.models.entities.PhieuMuon;
import com.example.library.code.services.serviceimp.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/")
public class CheckOutController {

    @Autowired
    private CheckOutService checkOutService;

//    thông báo gửi xác nhận thành công
    @GetMapping("check-out")
    public ModelAndView checkOutPage() {

        return null;
    }

    @GetMapping("thong-tin-muon-tra")
    public ModelAndView muonTraPage() {
        ModelAndView modelAndView = new ModelAndView("/user/muon-tra-sach-info");
        return modelAndView;
    }

    @PostMapping("tao-phieu-muon-tra")
    public PhieuMuon taoPhieuMuon(@RequestParam int maTk) {
        return checkOutService.taoPhieuMuon(maTk);
    }

    @GetMapping("lay-phieu-muon-tra")
    public List<LayPhieuMuonTraDto> layPhieuMuonTra(@RequestParam int maTk) {
        return checkOutService.layPhieuMuonTraInfo(maTk);
    }
}
