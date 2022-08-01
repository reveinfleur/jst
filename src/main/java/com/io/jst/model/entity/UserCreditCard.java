package com.io.jst.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserCreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cardId")
    private long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;

    private int price;

    @Column(unique = true)
    private String cardName;
    private String expireDate;
    private int cvc;
    private String cardNumber;


}
