package com.io.jst.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MailDto {

    private String mailTo;
    private String content;
    private int number;
}
