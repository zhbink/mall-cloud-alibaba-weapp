package com.micro.mall.Service;

import com.micro.mall.dto.AuthVo;

public interface AuthService {
    String setOpenId4Data(String rawData, String openId);

    AuthVo createToken(String userData);

}
