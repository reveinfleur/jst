package com.io.jst.repository.custom;

import com.io.jst.model.entity.QUsers;
import com.io.jst.model.entity.Users;
import com.io.jst.repository.custom.UserRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.security.core.userdetails.User;

import javax.persistence.EntityManager;

import static com.io.jst.model.entity.QUsers.users;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public void insertShopReservation(int id) {


    }
}
