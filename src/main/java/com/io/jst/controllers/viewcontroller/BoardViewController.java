package com.io.jst.controllers.viewcontroller;

import com.io.jst.model.dto.*;
import com.io.jst.model.entity.CustomerBoard;
import com.io.jst.security.PrincipalDetail;
import com.io.jst.service.boardServices.BoardService;
import com.io.jst.service.boardServices.FreeBoardService;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardViewController {

    private final BoardService boardService;
    private final FreeBoardService freeBoardService;



    @GetMapping("/CustomerServiceBoard")
    public String CustomerServiceBoard(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal PrincipalDetail principalDetail){

        model.addAttribute("boards", boardService.list(pageable));

        return "board/CustomerServiceBoard";
    }

    @GetMapping("/CustomerServiceBoard/search")
    public String CSBoardSearch(BoardSearchCondition boardSearchCondition, Model model){

        model.addAttribute("boards", boardService.search(boardSearchCondition));

        return "board/CustomerServiceBoard";
    }

    @GetMapping("/CustomerDetail")
    public String CustomerServiceDetail(@RequestParam long id, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail){
        String username = principalDetail.getUsername();
        CSBoardDto csBoardDto = boardService.findDetail(id);

        model.addAttribute("boards", boardService.findDetail(id));

        return "/board/CustomerDetail";
    }

    @GetMapping("/FreeBoard")
    public String FreeBoardForm(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal PrincipalDetail principalDetail){

        model.addAttribute("frees", freeBoardService.list(pageable));
        return "/board/FreeBoard";
    }

    @GetMapping("/FreeBoardAdd")
    public String FreeBoardAddForm(){
        return "/board/FreeBoardAdd";
    }

    @GetMapping("/FreeBoardDetail")
    public String FreeBoardDetailForm(@RequestParam Long id, Model model){

        model.addAttribute("frees", freeBoardService.findById(id));

        return "/board/FreeBoardDetail";
    }

    @GetMapping("/FreeBoardDetail/{id}")
    public String FreeBoardDetailForm2(@PathVariable Long id, Model model){

        model.addAttribute("frees", freeBoardService.findById(id));

        return "/board/FreeBoardDetail";
    }

}
