package com.io.jst.validator;

import com.io.jst.model.dto.PayPrice;
import com.io.jst.service.cardService.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class CardValidator implements Validator {

    private final CardService cardService;

    @Override
    public boolean supports(Class<?> clazz) {
        return PayPrice.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {



    }
}
