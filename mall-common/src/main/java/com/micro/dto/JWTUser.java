package com.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTUser {

    private String openId;
    private String nickName;
    private String avatarUrl;


}
