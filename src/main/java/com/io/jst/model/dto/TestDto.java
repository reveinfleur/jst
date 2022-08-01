package com.io.jst.model.dto;


import com.io.jst.model.entity.*;
import lombok.Data;

import java.util.List;


@Data
public class TestDto {

    private Long id;
    private String username;
    private String password;
    private String role;
    private String phoneNumber;
    private String address;
    private String email;
    private String birthday;
    private String provider;
    private String providerId;

    private List<Shop> shop;

    private List<Reply> reply;

    private List<FreeBoard> freeBoards;

    private List<CustomerBoard> customerBoards;

    private List<UserCreditCard> creditCards;

}
