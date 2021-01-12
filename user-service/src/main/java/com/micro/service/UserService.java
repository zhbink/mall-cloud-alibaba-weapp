package com.micro.service;


import com.micro.dto.SimpleUser;

public interface UserService {
    SimpleUser getSimpleUser(Integer id);

    Integer getUserIdFromOpenId(String Openid);
}
