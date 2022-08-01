package com.io.jst.model.dto;

import com.io.jst.model.entity.Users;
import lombok.Data;

import javax.persistence.*;

@Data
public class UserCreditCardDto {

    private long id;

    private Users users;

    private int price;
    private String cardName;
    private String expireDate;
    private int cvc;
    private String cardNumber;
}
