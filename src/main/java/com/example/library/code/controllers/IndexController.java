package com.example.library.code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1/")
public class IndexController {



    @GetMapping("index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("shop")
    public ModelAndView cuaHang() {
        ModelAndView modelAndView = new ModelAndView("shop-grid");
        return modelAndView;
    }

    @GetMapping("login")
    public ModelAndView dangNhap() {
        ModelAndView modelAndView = new ModelAndView("/login/login-form");
        return modelAndView;
    }

    @GetMapping("register")
    public ModelAndView dangKy() {
        ModelAndView modelAndView = new ModelAndView("/login/register-form");
        return modelAndView;
    }

}
