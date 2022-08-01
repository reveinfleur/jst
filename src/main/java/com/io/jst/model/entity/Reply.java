package com.io.jst.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Reply {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "replyId")
    private long id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne
    @JoinColumn(name="freeBoardId")
    private FreeBoard freeBoard;

    @ManyToOne
    @JoinColumn(name="userId")
    private Users users;

    @CreationTimestamp
    private LocalDateTime localDateTime;
}
