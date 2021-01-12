package com.micro.service;

import com.micro.mbg.model.Goods;

import java.util.List;

public interface GoodsUserService {
    List<Goods> getPosted(Integer userId, Integer page, Integer size);
}
