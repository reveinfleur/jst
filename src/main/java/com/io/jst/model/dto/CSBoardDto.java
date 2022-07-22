package com.io.jst.model.dto;

import com.io.jst.model.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CSBoardDto {
    private Long id;

    private String title;

    private LocalDateTime localDateTime;

    private Users users;

    private String content;


    public CSBoardDto(Long id, String title, LocalDateTime localDateTime, Users users, String content) {
        this.id = id;
        this.title = title;
        this.localDateTime = localDateTime;
        this.users = users;
        this.content = content;
    }
}
