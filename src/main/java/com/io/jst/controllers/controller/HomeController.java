package com.io.jst.controllers.controller;

import com.io.jst.model.dto.ResponseDto;
import com.io.jst.model.dto.ShopDto;
import com.io.jst.service.shopService.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ShopService shopService;

    @GetMapping("/")
    public String main(Model model, Pageable pageable){

        Page<ShopDto> shopDto = shopService.list(pageable);

        model.addAttribute("shops", shopDto);
        return "index";
    }

    @PostMapping("/shop/add")
    public String shopAdd(ShopDto shopDto, MultipartFile file) throws IOException {
        System.out.println("add 들어옴");


        shopService.save(shopDto, file);

        return "redirect:/";
    }

}
