package com.io.jst.model.dto;

import com.io.jst.model.entity.FreeBoard;
import com.io.jst.model.entity.Users;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class ReplyDto {

    private Long id;
    private Long boardId;

    private String content;

    private FreeBoard freeBoard;

    private Users users;

    private LocalDateTime localDateTime;

    public ReplyDto(Long id, Long boardId, String content, FreeBoard freeBoard, Users users, LocalDateTime localDateTime) {
        this.id = id;
        this.boardId = boardId;
        this.content = content;
        this.freeBoard = freeBoard;
        this.users = users;
        this.localDateTime = localDateTime;
    }
}
