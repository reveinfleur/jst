package com.io.jst.controllers.formcontroller;

import com.io.jst.controllers.ajaxcontroller.AjaxUserController;
import com.io.jst.model.dto.*;
import com.io.jst.security.PrincipalDetail;
import com.io.jst.service.SendMailService;
import com.io.jst.service.cardService.CardService;
import com.io.jst.service.shopService.ShopService;
import com.io.jst.service.userServices.UserService;
import com.io.jst.validator.CardValidator;
import com.io.jst.validator.ShopValidator;
import com.io.jst.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class formController {

    private final UserService userService;
    private final ShopService shopService;
    private final CardService cardService;
    private final ShopValidator shopValidator;
    private final UserValidator userValidator;
    private final SendMailService sendMailService;
    private final AjaxUserController ajaxUserController;
    private final CardValidator cardValidator;


    @PostMapping("/shop/add")
    public String shopAdd(@Validated @ModelAttribute ShopDto shopDto, BindingResult bindingResult, MultipartFile file) throws IOException {


        shopValidator.validate(shopDto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return "/shop/productAdd";
        }
        shopService.save(shopDto, file);

        return "redirect:/";
    }

    @PostMapping("/user/joinProc")
    public String userJoin(@Validated @ModelAttribute UsersDto usersDto, BindingResult bindingResult){

        MailDto mailDto = ajaxUserController.getMailDto();
        usersDto.setCheckSum(mailDto.getNumber());
        userValidator.validate(usersDto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return "/user/joinForm";
        }
        userService.join(usersDto);
        return "redirect:/";
    }
}
