package com.micro.service;

import com.micro.dto.SimpleUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user")
public interface FeignUserService {

    // http://user/getsimpleuser/{id}
    @GetMapping("/get/simpleuser/{id}")
    SimpleUser getSimpleUser(@PathVariable Integer id);

    @GetMapping("getid/{openId}")
    Integer getUserIdFromOpenId(@PathVariable String openId);


}
