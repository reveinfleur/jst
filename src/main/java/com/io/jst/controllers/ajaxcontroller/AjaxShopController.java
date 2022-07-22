package com.io.jst.controllers.ajaxcontroller;

import com.io.jst.model.dto.CSBoardDto;
import com.io.jst.model.dto.ResponseDto;
import com.io.jst.model.dto.ShopDto;
import com.io.jst.service.shopService.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class AjaxShopController {
    private final ShopService shopService;

   /* @PostMapping("/add")
    public ResponseDto<Integer> shopAdd(@RequestBody ShopDto shopDto){
        System.out.println("add 들어옴");

        shopService.save(shopDto);

        System.out.println(shopDto.toString());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }*/
}
