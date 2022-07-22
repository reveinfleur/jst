package com.io.jst.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "shopId")
    private long id;

    private int price;
    @Column(unique = true)
    private String productName;

   /* @OneToMany(mappedBy = "shop")
    private List<File> files;*/

    private String fileName;
    private String filePath;
    private int star;
    private String content;

    private String startDay;

    private String endDay;
}
