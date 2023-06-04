package com.example.library.code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1/")
public class CartController {

    @GetMapping("cart")
    public ModelAndView cartPage() {
        ModelAndView modelAndView = new ModelAndView("cart");
        return modelAndView;
    }
}
