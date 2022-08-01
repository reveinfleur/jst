package com.io.jst.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@SequenceGenerator(
        name = "USERS_SEQ_GEN",
        sequenceName = "USERS_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Users {
    public Users() {

    }

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

    private String provider;
    private String providerId;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    private List<Shop> shop;

    @OneToMany(mappedBy = "users")
    private List<UserCreditCard> creditCards;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("localDateTime desc")
    private List<Reply> reply;

    @OneToMany(mappedBy = "users",cascade = CascadeType.REMOVE )
    private List<FreeBoard> freeBoards;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    private List<CustomerBoard> customerBoards;

    @CreationTimestamp
    private LocalDateTime localDateTime;


}


