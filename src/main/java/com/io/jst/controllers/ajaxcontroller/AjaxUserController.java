package com.io.jst.controllers.ajaxcontroller;

import com.io.jst.model.dto.MailDto;
import com.io.jst.model.dto.ResponseDto;
import com.io.jst.model.dto.UserCreditCardDto;
import com.io.jst.model.dto.UsersDto;
import com.io.jst.service.SendMailService;
import com.io.jst.service.userServices.UserService;
import com.io.jst.validator.MailValidator;
import com.io.jst.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AjaxUserController {

    private final UserService userService;
    private final SendMailService sendMailService;
    private final MailValidator mailValidator;

    private MailDto mailDto = new MailDto();

    @PostMapping("/joinProcEmail")
    public ResponseDto<Integer> userJoinEmail(@Validated @RequestBody UsersDto users,BindingResult bindingResult){

        System.out.println("emai;"+users.getEmail());

        mailValidator.validate(users, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return new ResponseDto<Integer>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 1);
        }

        mailDto.setNumber(sendMailService.mailSend(users.getEmail()));
        System.out.println("요청 check"+mailDto.getNumber());

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    public MailDto getMailDto(){
        return mailDto;
    }


}
