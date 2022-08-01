package com.io.jst.controllers.viewcontroller;

import com.io.jst.model.dto.ShopDto;
import com.io.jst.model.dto.UserCreditCardDto;
import com.io.jst.model.dto.UsersDto;
import com.io.jst.model.dto.PayPrice;
import com.io.jst.security.PrincipalDetail;
import com.io.jst.service.cardService.CardService;
import com.io.jst.service.shopService.ShopService;
import com.io.jst.service.userServices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final UserService userService;
    private final CardService cardService;

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("shopDto", new ShopDto());
        return "/shop/productAdd";
    }

    @GetMapping("/detail/{id}")
    public String detailForm(@PathVariable Long id, Model model){
        ShopDto shopDto = shopService.findDetail(id);

        model.addAttribute("shops", shopDto);
        return "/shop/productDetail";
    }

    @GetMapping("/payment/{id}")
    public String paymentForm(@PathVariable Long id, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail){
        ShopDto shopDto  = shopService.findDetail(id);
        UsersDto usersDto = userService.findByUserName(principalDetail.getUsername());
        UserCreditCardDto userCreditCardDto = cardService.getCardSelect();


        if(usersDto.getRole().equals("ROLE_VIP")){
            int discount = (shopDto.getPrice()*10/100);
            shopDto.setDiscountPrice(discount);
            shopDto.setPriceTotal(shopDto.getPrice()-discount);
        }

        shopDto.setPriceTotal(shopDto.getPrice());

        if(userCreditCardDto!=null)
        {
            model.addAttribute("secards", userCreditCardDto);
        }
        model.addAttribute("shops", shopDto);
        model.addAttribute("cards", usersDto.getCreditCards());

        return "/shop/payment";
    }

    @GetMapping("/successForm")
    public String successForm(){
        return "/shop/successForm";
    }



}
