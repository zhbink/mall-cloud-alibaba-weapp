package com.micro.mall.service;

import com.github.pagehelper.PageInfo;
import com.micro.mall.dto.CatalogPageVo;
import com.micro.mall.dto.IndexPageVo;
import com.micro.mbg.model.Category;
import com.micro.mbg.model.Goods;


import java.util.List;

public interface IndexService {
    IndexPageVo getIndex(int page, int size);

    PageInfo<Goods> getIndexMore(int page, int size);

    CatalogPageVo getCatalogIndex();

    List<Category> getSubCatalogById(int id);

    List<Goods> goodsTest();
}
