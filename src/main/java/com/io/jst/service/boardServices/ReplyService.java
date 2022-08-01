package com.io.jst.service.boardServices;

import com.io.jst.model.dto.FreeBoardDto;
import com.io.jst.model.dto.ReplyDto;
import com.io.jst.model.entity.Reply;
import com.io.jst.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;


    public void replySave(ReplyDto replyDto){
        ModelMapper modelMapper = new ModelMapper();
        Reply reply = modelMapper.map(replyDto, Reply.class);

        replyRepository.save(reply);
    }

    public List<ReplyDto> findByBoardId(Long id){


        return null;
    }

}
