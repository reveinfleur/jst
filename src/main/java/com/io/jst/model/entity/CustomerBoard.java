package com.io.jst.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class CustomerBoard {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cuboardId")
    private Long id;

    @Column(unique = true)
    private String title;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;

    @Lob
    private String content;
}
