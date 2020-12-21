package com.micro.mall.service;


import com.micro.mall.dto.SimpleUser;
import com.micro.mbg.model.Goods;

import java.util.List;

public interface UserService {
    SimpleUser getSimpleUser(Integer id);

    Integer getUserIdFromOpenId(String Openid);
}
