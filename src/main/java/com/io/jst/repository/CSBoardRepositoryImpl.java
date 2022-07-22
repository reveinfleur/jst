package com.io.jst.repository;

import com.io.jst.model.dto.BoardSearchCondition;
import com.io.jst.model.entity.CustomerBoard;
import com.io.jst.model.entity.QCustomerBoard;
import com.io.jst.model.entity.QUsers;
import com.io.jst.model.entity.Users;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.io.jst.model.entity.QCustomerBoard.customerBoard;


public class CSBoardRepositoryImpl implements CSBoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CSBoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<CustomerBoard> search(BoardSearchCondition condition) {

        List<CustomerBoard> list = queryFactory
                .selectFrom(customerBoard)
                .where(customerBoard.title.contains(condition.getSearchText()))
                .fetch();

        return list;
    }


}
