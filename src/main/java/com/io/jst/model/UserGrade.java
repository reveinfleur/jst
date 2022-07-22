package com.io.jst.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserGrade {
    COMMON("ROLE_BASIC"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String value;
}
