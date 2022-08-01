package com.io.jst.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class FreeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "freeBoardId")
    private Long id;

    @Column(unique = true)
    private String title;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;

    @OneToMany(mappedBy = "freeBoard", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"freeBoard"})
    @OrderBy("localDateTime desc")
    private List<Reply> reply;

    @Lob
    private String content;
}
