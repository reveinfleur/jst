package com.io.jst.controllers.ajaxcontroller;


import com.io.jst.model.dto.CSBoardDto;
import com.io.jst.model.dto.FreeBoardDto;
import com.io.jst.model.dto.ResponseDto;
import com.io.jst.model.dto.UsersDto;
import com.io.jst.security.PrincipalDetail;
import com.io.jst.service.boardServices.BoardService;
import com.io.jst.service.boardServices.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class AjaxBoardController {

    private final BoardService boardService;
    private final FreeBoardService freeBoardService;

    @PostMapping("/save")
    public ResponseDto<Integer> boardJoin(@RequestBody CSBoardDto csBoardDto, @AuthenticationPrincipal PrincipalDetail principalDetail){

        csBoardDto.setUsers(principalDetail.getUsers());
        boardService.save(csBoardDto);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping ("/delete")
    public ResponseDto<Integer> boardDelete(@RequestBody CSBoardDto csBoardDto){
        long userId = csBoardDto.getId();

        boardService.delete(userId);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/FreeBoardSave")
    public ResponseDto<Integer> FreeBoardJoin(@RequestBody FreeBoardDto freeBoardDto, @AuthenticationPrincipal PrincipalDetail principalDetail){

        freeBoardDto.setUsers(principalDetail.getUsers());
        freeBoardService.save(freeBoardDto);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }





}
