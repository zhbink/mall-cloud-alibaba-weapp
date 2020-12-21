package com.micro.mall.service;

import com.micro.mall.dto.PostExample;
import com.micro.mbg.model.Category;
import com.micro.mbg.model.Region;

import java.util.List;

public interface PostService {
    void postGoods(PostExample post);

    void deleteGoods(int goodsId) throws Exception;

    List<Region> getRegionList(int regionId);

    List<Category> getCateList(int cateId);
}
