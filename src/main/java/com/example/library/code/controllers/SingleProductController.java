package com.example.library.code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1/")
public class SingleProductController {

    @GetMapping("single-product")
    public ModelAndView donSanPhamPage() {
        ModelAndView modelAndView = new ModelAndView("single-product");
        return modelAndView;
    }
}
