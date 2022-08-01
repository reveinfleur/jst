package com.io.jst.validator;

import com.io.jst.model.dto.UsersDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MailValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UsersDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UsersDto usersDto = (UsersDto) target;
        String email = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

        System.out.println(usersDto.getCheckSum());
        if(usersDto.getInputCheckSum()!=usersDto.getCheckSum()){
            errors.rejectValue("inputCheckSum", "errorPattern");
        }
    }
}
