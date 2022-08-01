package com.io.jst.model.dto;

import com.io.jst.model.entity.Reply;
import com.io.jst.model.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class FreeBoardDto {
    private Long id;

    private String title;

    private LocalDateTime localDateTime;

    private List<Reply> reply;

    private Users users;

    private String content;



    public FreeBoardDto(Long id, String title, LocalDateTime localDateTime, Users users, String content) {
        this.id = id;
        this.title = title;
        this.localDateTime = localDateTime;
        this.users = users;
        this.content = content;
    }
}
