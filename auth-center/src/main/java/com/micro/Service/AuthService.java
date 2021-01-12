package com.micro.Service;

import com.micro.dto.AuthVo;

public interface AuthService {
    String setOpenId4Data(String rawData, String openId);

    AuthVo createToken(String userData);

}
