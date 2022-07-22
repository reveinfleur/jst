package com.io.jst.repository;

import com.io.jst.model.entity.CustomerBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CSBoardRepository extends JpaRepository<CustomerBoard, Long>, CSBoardRepositoryCustom {

}
