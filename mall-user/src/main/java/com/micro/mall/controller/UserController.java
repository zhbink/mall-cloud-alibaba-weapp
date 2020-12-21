package com.micro.mall.controller;

import com.micro.mall.dto.CommonResult;
import com.micro.mall.dto.SimpleUser;
import com.micro.mall.service.UserService;
import com.micro.mbg.mapper.UserMapper;
import com.micro.mbg.model.Goods;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/get/simpleuser/{id}")
    public SimpleUser getSimpleUser(@PathVariable Integer id) {
        return userService.getSimpleUser(id);
    }

    @GetMapping("/getid/{openId}")
    public Integer getUserIdFromOpenId(@PathVariable String openId) {
        return userService.getUserIdFromOpenId(openId);
    }





}
