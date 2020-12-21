package com.micro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.micro.mall.dto.SimpleUser;
import com.micro.mall.service.UserService;
import com.micro.mbg.mapper.GoodsMapper;
import com.micro.mbg.mapper.UserMapper;
import com.micro.mbg.model.Goods;
import com.micro.mbg.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final GoodsMapper goodsMapper;

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

    public Integer getUserIdFromOpenId(String Openid) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openId", Openid);
        return userMapper.selectOneByExample(example).getId();
    }


}
