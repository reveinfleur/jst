package com.io.jst.controllers.viewcontroller;

import com.io.jst.model.dto.ShopDto;
import com.io.jst.security.PrincipalDetail;
import com.io.jst.service.shopService.ShopService;
import com.io.jst.validator.ShopValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ShopService shopService;


    @GetMapping("/")
    public String main(Model model, Pageable pageable, @AuthenticationPrincipal PrincipalDetail principalDetail){

        Page<ShopDto> shopDto = shopService.list(pageable);

        model.addAttribute("shops", shopDto);
        return "index";
    }

}
