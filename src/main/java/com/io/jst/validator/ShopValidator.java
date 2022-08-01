package com.io.jst.validator;

import com.io.jst.model.dto.ShopDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ShopValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ShopDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ShopDto shopDto = (ShopDto) target;
        if (!StringUtils.hasText(shopDto.getProductName())){
            errors.rejectValue("productName", "required");
        }
        if (!StringUtils.hasText(shopDto.getContent())){
            errors.rejectValue("content", "required");
        }
        if (shopDto.getPrice()==0 || shopDto.getPrice() < 1000 || shopDto.getPrice() > 1000000){
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if (shopDto.getContent().length()<=10 || shopDto.getContent().length()>=1000){
            errors.rejectValue("content", "range", new Object[]{10, 1000}, null);
        }



    }
}
