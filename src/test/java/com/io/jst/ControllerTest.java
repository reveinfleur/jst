package com.io.jst;

import com.io.jst.model.dto.BoardSearchCondition;
import com.io.jst.model.entity.CustomerBoard;
import com.io.jst.model.entity.QCustomerBoard;
import com.io.jst.model.entity.Users;
import com.io.jst.repository.CSBoardRepository;
import com.io.jst.repository.UserRepository;
import com.io.jst.service.boardServices.BoardService;
import com.io.jst.service.userServices.UserService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.io.jst.model.entity.QCustomerBoard.customerBoard;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
@Transactional
public class ControllerTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CSBoardRepository csBoardRepository;

    @Autowired
    private UserService userService;

    @Autowired
    EntityManager em;



    @Test
    public void user(){
        Users users = new Users();
        String username="user";
        users = userRepository.findByUsername(username).orElseThrow(()->{
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : ");
        });
        System.out.println(users);
    }

    @Test
    public void pageTest(){


        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        List<CustomerBoard> result = queryFactory
                .selectFrom(customerBoard)
                .orderBy(customerBoard.users.username.desc())
                .offset(0) //offset은 0부터 시작(zero index), 현재 코드에서는 1번부터 데이터를 가져오도록 하므로 맨 처음 데이터는 생략됨
                .limit(2) //최대 2건 조회
                .fetch();
        for(CustomerBoard s : result){
            System.out.println(s);
        }

    }

}
