package com.io.jst.model.dto;

import com.io.jst.model.entity.Shop;
import lombok.Data;

import javax.persistence.*;

@Data
public class FileDto {


    private long id;

    private String fileName;
    private String filePath;

    private Shop shop;
}
