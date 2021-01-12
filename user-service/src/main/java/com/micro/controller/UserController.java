package com.micro.controller;

import com.micro.dto.SimpleUser;
import com.micro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
