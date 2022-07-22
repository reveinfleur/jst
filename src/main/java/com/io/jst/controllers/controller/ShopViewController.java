package com.io.jst.controllers.controller;

import com.io.jst.model.dto.ShopDto;
import com.io.jst.service.shopService.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopViewController {

    private final ShopService shopService;

    @GetMapping("/add")
    public String addForm(){
        return "/shop/productAdd";
    }

    @GetMapping("/detail/{id}")
    public String detailForm(@PathVariable Long id, Model model){
        ShopDto shopDto = shopService.findDetail(id);
        model.addAttribute("shops", shopDto);
        return "/shop/productDetail";
    }

    @GetMapping("/payment")
    public String paymentForm(){
        return "/shop/payment";
    }

    @GetMapping("/success")
    public String successForm(){
        return "/shop/successForm";
    }

}
