package com.io.jst.controllers.ajaxcontroller;

import com.io.jst.model.dto.*;
import com.io.jst.model.entity.Users;
import com.io.jst.security.PrincipalDetail;
import com.io.jst.service.cardService.CardService;
import com.io.jst.service.shopService.ShopService;
import com.io.jst.service.userServices.UserService;
import com.io.jst.validator.CardValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class AjaxShopController {

    private final ShopService shopService;
    private final CardService cardService;
    private final UserService userService;

    @PostMapping("/detail/add")
    public ResponseDto<Integer> shopAdd(@RequestBody ShopDto shopDto){
        System.out.println("add 들어옴"+ shopDto.toString());

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/addPay")
    public ResponseDto<Integer> addCreditCard(@RequestBody UserCreditCardDto userCreditCardDto, @AuthenticationPrincipal PrincipalDetail principalDetail){

        userCreditCardDto.setUsers(principalDetail.getUsers());

        cardService.addCard(userCreditCardDto);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/payment")
    public ResponseDto<Integer> cardSelect(@RequestBody UserCreditCardDto userCreditCardDto){

        UserCreditCardDto userCreditCardDto1 = cardService.findById(userCreditCardDto.getId());
        cardService.setCardSelect(userCreditCardDto1);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @PostMapping("/payment/go")
    public ResponseDto<Integer> goPayment(@Validated @RequestBody PayPrice payPrice, @AuthenticationPrincipal PrincipalDetail principalDetail){

        if(!cardService.errorCheck(payPrice)){
            return new ResponseDto<Integer>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 1);
        }
        UsersDto usersDto = userService.findByUserName(principalDetail.getUsername());
        if(usersDto.getRole().equals("ROLE_VIP")){
            payPrice.setPrice(payPrice.getPrice()-(payPrice.getPrice() * 10 / 100));
        }

        cardService.goPayment(payPrice.getCardName(), payPrice.getPrice(), principalDetail.getUsername(), payPrice.getId());

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
