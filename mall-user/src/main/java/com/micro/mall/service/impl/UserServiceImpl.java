package com.micro.mall.service.impl;

import com.micro.mall.dto.SimpleUser;
import com.micro.mall.service.UserService;
import com.micro.mbg.mapper.UserMapper;
import com.micro.mbg.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public SimpleUser getSimpleUser(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        SimpleUser simpleUser = SimpleUser.builder()
                .avatarUrl(user.getAvatarUrl())
                .nickName(user.getNickName())
                .registerTime(user.getRegisterTime())
                .openId(user.getOpenId())
                .build();
        return simpleUser;
    }
}
