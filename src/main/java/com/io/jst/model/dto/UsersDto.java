package com.io.jst.model.dto;

import com.io.jst.model.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.List;

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
    private String provider;
    private String providerId;

    private int checkSum;
    private int inputCheckSum;
    private String zipcode;
    private String addressDetail;
    private String addressMain;

    private List<Shop> shop;

    private List<Reply> reply;

    private List<FreeBoard> freeBoards;

    private List<CustomerBoard> customerBoards;

    private List<UserCreditCard> creditCards;

}
