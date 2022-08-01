package com.io.jst.controllers.viewcontroller;

import com.io.jst.model.dto.UsersDto;
import com.io.jst.security.PrincipalDetail;
import com.io.jst.service.userServices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserViewController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "/user/loginForm";
    }
    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("usersDto", new UsersDto());
        return "/user/joinForm";
    }
    @GetMapping("/addPay")
    public String payAdd(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model){

        UsersDto usersDto = userService.findByUserName(principalDetail.getUsername());
        model.addAttribute("users", usersDto);
        return "/user/addPayment";
    }
    @GetMapping("/userDetail")
    public String userDetailForm(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail){
        UsersDto usersDto = userService.findByUserName(principalDetail.getUsername());


        int cuSize = usersDto.getCustomerBoards().size();
        int frSize = usersDto.getFreeBoards().size();
        int reSize = usersDto.getReply().size();


        System.out.println(usersDto.getCustomerBoards());
        System.out.println(usersDto.getCreditCards());
        System.out.println(usersDto.getShop());

        model.addAttribute("users", userService.findByUserName(principalDetail.getUsername()));
        model.addAttribute("frSize", cuSize);
        model.addAttribute("cuSize", frSize);
        model.addAttribute("reSize", reSize);

        return "/user/userDetail";
    }

    @GetMapping("/userDetailEdit")
    public String userDetailForm2(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail){
        UsersDto usersDto = userService.findByUserName(principalDetail.getUsername());

        model.addAttribute("users", usersDto);

        return "/user/userDetailEdit";
    }


}
