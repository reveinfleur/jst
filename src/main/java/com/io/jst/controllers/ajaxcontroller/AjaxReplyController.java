package com.io.jst.controllers.ajaxcontroller;

import com.io.jst.model.dto.FreeBoardDto;
import com.io.jst.model.dto.ReplyDto;
import com.io.jst.model.dto.ResponseDto;
import com.io.jst.model.entity.FreeBoard;
import com.io.jst.security.PrincipalDetail;
import com.io.jst.service.boardServices.FreeBoardService;
import com.io.jst.service.boardServices.ReplyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class AjaxReplyController {

    private final FreeBoardService freeBoardService;
    private final ReplyService replyService;

    @PostMapping("/replySave")
    public ResponseDto<Integer> ReplySave(@RequestBody ReplyDto replyDto, @AuthenticationPrincipal PrincipalDetail principalDetail){

        FreeBoardDto freeBoardDto= freeBoardService.findById(replyDto.getBoardId());
        ModelMapper modelMapper = new ModelMapper();
        FreeBoard freeBoard = modelMapper.map(freeBoardDto, FreeBoard.class);
        replyDto.setUsers(principalDetail.getUsers());
        replyDto.setFreeBoard(freeBoard);
        replyService.replySave(replyDto);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }
}
