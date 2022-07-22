package com.io.jst.controllers.ajaxcontroller;

import com.io.jst.model.dto.CSBoardDto;
import com.io.jst.model.dto.ResponseDto;
import com.io.jst.service.boardServices.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class AjaxBoardDetailController {

    private final BoardService boardService;

    @PutMapping ("/modify")
    public ResponseDto<Integer> boardUpdate(@RequestBody CSBoardDto csBoardDto){
        System.out.println("board modify 컨트롤러 들어옴");
        System.out.println(csBoardDto.toString());

        boardService.update(csBoardDto);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
