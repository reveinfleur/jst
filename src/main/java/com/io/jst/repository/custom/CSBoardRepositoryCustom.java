package com.io.jst.repository.custom;

import com.io.jst.model.dto.BoardSearchCondition;
import com.io.jst.model.dto.CSBoardDto;
import com.io.jst.model.entity.CustomerBoard;


import java.util.List;

public interface CSBoardRepositoryCustom {

    List<CustomerBoard> search(BoardSearchCondition condition);

}
