package com.io.jst.validator;

import com.io.jst.model.dto.TestDto;
import com.io.jst.model.dto.UsersDto;
import com.io.jst.service.userServices.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.regex.Pattern;


@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UsersDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UsersDto usersDto = (UsersDto) target;
        HashMap<String, Boolean> hashMap = userService.errorCheck(usersDto);

        String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$";
        String phone = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";
        String email = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

        System.out.println(usersDto.getCheckSum());
        if(usersDto.getInputCheckSum()!=usersDto.getCheckSum()){
            errors.rejectValue("inputCheckSum", "errorPattern");
        }
        if(hashMap.get("username")==true){
            errors.rejectValue("username", "exist", new Object[]{usersDto.getUsername()}, null);
        }
        if(hashMap.get("phoneNumber")==true){
            errors.rejectValue("phoneNumber", "exist", new Object[]{usersDto.getPhoneNumber()}, null);
        }
        if(hashMap.get("email")==true){
            errors.rejectValue("email", "exist", new Object[]{usersDto.getEmail()}, null);
        }

        if(usersDto.getUsername().length()>=12 || usersDto.getUsername().length()<3){
            errors.rejectValue("username", "range", new Object[]{3, 12}, null);
        }
        if(!Pattern.matches(pattern, usersDto.getUsername())){
            errors.rejectValue("username", "characters");
        }
        if(usersDto.getPassword().length()<=3 || usersDto.getPassword().length()>=15){
            errors.rejectValue("password", "range", new Object[]{3, 15}, null);
        }
        if(!Pattern.matches(phone, usersDto.getPhoneNumber())){
            errors.rejectValue("phoneNumber", "errorPattern");
        }
        if(!Pattern.matches(email, usersDto.getEmail())){
            errors.rejectValue("email", "errorPattern");
        }



    }
}
