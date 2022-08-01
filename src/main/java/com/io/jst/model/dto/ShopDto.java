package com.io.jst.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class ShopDto {

    private long id;

    private int price;
    private int discountPrice;
    private int priceTotal;

    private String productName;

    private String content;

    private String fileName;
    private String filePath;

    private String startDay;
    private String endDay;

    public ShopDto() {
    }

    public ShopDto(long id, int price, String productName, String content, String fileName, String filePath, String startDay, String endDay) {
        this.id = id;
        this.price = price;
        this.productName = productName;
        this.content = content;
        this.fileName = fileName;
        this.filePath = filePath;
        this.startDay = startDay;
        this.endDay = endDay;
    }
}
