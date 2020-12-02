package com.micro.mall.dto;

import lombok.Data;


@Data
public class AuthDTO {
    private String code;
    private DetailAuthDTO detail;
}
