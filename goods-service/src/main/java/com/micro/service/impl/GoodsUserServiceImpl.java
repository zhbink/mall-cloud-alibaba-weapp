package com.micro.service.impl;

import com.github.pagehelper.PageHelper;
import com.micro.service.GoodsUserService;
import com.micro.mbg.mapper.GoodsMapper;
import com.micro.mbg.model.Goods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsUserServiceImpl implements GoodsUserService {
    private final GoodsMapper goodsMapper;

    public List<Goods> getPosted(Integer userId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = new Example(Goods.class);
        example.createCriteria().andEqualTo("sellerId", userId);
        return goodsMapper.selectByExample(example);
    }
}
