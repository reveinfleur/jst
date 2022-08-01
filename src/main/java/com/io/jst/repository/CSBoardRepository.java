package com.io.jst.repository;

import com.io.jst.model.entity.CustomerBoard;
import com.io.jst.repository.custom.CSBoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CSBoardRepository extends JpaRepository<CustomerBoard, Long>, CSBoardRepositoryCustom {

}
