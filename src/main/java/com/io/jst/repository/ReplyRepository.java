package com.io.jst.repository;

import com.io.jst.model.entity.Reply;
import com.io.jst.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByFreeBoardId(Long boardId);
}
