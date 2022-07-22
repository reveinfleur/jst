package com.io.jst.model.dto;

import lombok.Data;

@Data
public class UsersDto {


    private Long id;
    private String username;
    private String password;
    private String role;
    private String phoneNumber;
    private String address;
    private String email;
    private String birthday;
    private int bankAccount;

}
