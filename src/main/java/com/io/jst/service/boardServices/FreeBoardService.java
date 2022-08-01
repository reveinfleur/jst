package com.io.jst.service.boardServices;

import com.io.jst.model.dto.CSBoardDto;
import com.io.jst.model.dto.FreeBoardDto;
import com.io.jst.model.dto.ReplyDto;
import com.io.jst.model.entity.FreeBoard;
import com.io.jst.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FreeBoardService {

    private final FreeBoardRepository freeBoardRepository;

    @Transactional(readOnly = true)
    public Page<FreeBoardDto> list(Pageable pageable){
        Page<FreeBoard> page =freeBoardRepository.findAll(pageable);

        Page<FreeBoardDto> map = page.map(FreeBoard -> new FreeBoardDto(FreeBoard.getId(),
                FreeBoard.getTitle(), FreeBoard.getLocalDateTime(), FreeBoard.getUsers(), FreeBoard.getContent()));
        return map;
    }
    @Transactional
    public void save(FreeBoardDto freeBoardDto){
        ModelMapper modelMapper = new ModelMapper();
        FreeBoard freeBoard = modelMapper.map(freeBoardDto, FreeBoard.class);
        System.out.println("servicce" + freeBoard.toString());
        freeBoardRepository.save(freeBoard);
    }

    @Transactional
    public void replySave(ReplyDto replyDto){
        ModelMapper modelMapper = new ModelMapper();

    }

    @Transactional(readOnly = true)
    public FreeBoardDto findById(Long id){
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
        });

        ModelMapper modelMapper = new ModelMapper();
        FreeBoardDto freeBoardDto = modelMapper.map(freeBoard, FreeBoardDto.class);

        return freeBoardDto;
    }
}
