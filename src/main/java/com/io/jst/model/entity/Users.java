package com.io.jst.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@SequenceGenerator(
        name = "USERS_SEQ_GEN",
        sequenceName = "USERS_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ_GEN")
    @Column(name = "userId")
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
    private String role;

    @Column(unique = true)
    private String phoneNumber;
    private String address;
    private String email;
    private String birthday;
    private int price;
    private int bankAccount;

    @OneToMany(mappedBy = "users")
    private List<UserCreditCard> creditCards;

    @CreationTimestamp
    private LocalDateTime localDateTime;

}


