package com.io.jst.controllers.ajaxcontroller;

import com.io.jst.model.dto.ResponseDto;
import com.io.jst.model.dto.UsersDto;
import com.io.jst.service.userServices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AjaxUserController {

    private final UserService userService;

    @PostMapping("/joinProc")
    public ResponseDto<Integer> userJoin(@RequestBody UsersDto users){

        userService.join(users);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
