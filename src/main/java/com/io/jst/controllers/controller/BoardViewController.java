package com.io.jst.controllers.controller;

import com.io.jst.model.dto.BoardSearchCondition;
import com.io.jst.model.dto.CSBoardDto;
import com.io.jst.model.dto.ShopDto;
import com.io.jst.model.entity.CustomerBoard;
import com.io.jst.security.PrincipalDetail;
import com.io.jst.service.boardServices.BoardService;
import com.io.jst.service.shopService.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardViewController {

    private final BoardService boardService;


    @GetMapping("/CustomerServiceBoard")
    public String CustomerServiceBoard(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal PrincipalDetail principalDetail){

        Page<CSBoardDto> boards = boardService.list(pageable);

        model.addAttribute("boards", boardService.list(pageable));

        return "board/CustomerServiceBoard";
    }

    @GetMapping("/CustomerServiceBoard/search")
    public String CSBoardSearch(BoardSearchCondition boardSearchCondition, Model model){

        List<CustomerBoard> list = boardService.search(boardSearchCondition);
        model.addAttribute("boards", list);

        return "board/CustomerServiceBoard";
    }

    @GetMapping("/CustomerDetail")
    public String CustomerServiceDetail(@RequestParam long id, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail){
        String username = principalDetail.getUsername();
        CSBoardDto csBoardDto = boardService.findDetail(id);

        model.addAttribute("boards", csBoardDto);

        return "/board/CustomerDetail";
    }
}
