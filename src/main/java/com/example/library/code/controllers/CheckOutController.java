package com.example.library.code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1/")
public class CheckOutController {

//    thông báo gửi xác nhận thành công
    @GetMapping("check-out")
    public ModelAndView checkOutPage() {

        return null;
    }
}
