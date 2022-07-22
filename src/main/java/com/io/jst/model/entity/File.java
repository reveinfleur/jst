/*
package com.io.jst.model.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class File {

    @Id
    @Column(name = "fileId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String fileName;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "shopId")
    private Shop shop;
}
*/
