package com.io.jst.service.boardServices;

import com.io.jst.model.UserGrade;
import com.io.jst.model.dto.BoardSearchCondition;
import com.io.jst.model.dto.CSBoardDto;
import com.io.jst.model.entity.CustomerBoard;
import com.io.jst.model.entity.Users;
import com.io.jst.repository.CSBoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final CSBoardRepository csBoardRepository;
    @Transactional
    public void save(CSBoardDto csBoardDto){

        ModelMapper modelMapper = new ModelMapper();

        CustomerBoard customerBoard = modelMapper.map(csBoardDto, CustomerBoard.class);

        csBoardRepository.save(customerBoard);

    }
    @Transactional
    public void delete(Long userId){

        csBoardRepository.deleteById(userId);

    }

    @Transactional
    public void update(CSBoardDto csBoardDto){

        CustomerBoard board = csBoardRepository.findById(csBoardDto.getId())
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                }); // 영속화 완료

        board.setTitle(csBoardDto.getTitle());
        board.setContent(csBoardDto.getContent());

    }


    @Transactional(readOnly = true)
    public Page<CSBoardDto> list(Pageable pageable){
        Page<CustomerBoard> page = csBoardRepository.findAll(pageable);


        Page<CSBoardDto> map = page.map(CustomerBoard -> new CSBoardDto(CustomerBoard.getId(),
                CustomerBoard.getTitle(), CustomerBoard.getLocalDateTime(), CustomerBoard.getUsers(), CustomerBoard.getContent()));
        return map;
    }

    @Transactional(readOnly = true)
    public List<CustomerBoard> search(BoardSearchCondition condition){

        return csBoardRepository.search(condition);
    }

    @Transactional
    public CSBoardDto findDetail(Long id){
        ModelMapper modelMapper = new ModelMapper();

        CustomerBoard board = csBoardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                }); // 영속화 완료
        CSBoardDto map =  modelMapper.map(board, CSBoardDto.class);
        return map;
    }


}
