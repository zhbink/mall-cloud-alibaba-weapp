package com.micro.dto;

import com.micro.mbg.model.Category;
import com.micro.mbg.model.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPageVo {

    /*同一个父分类下的兄弟分类*/
    private List<Category> brotherCategory;

    /*当前分类的商品列表*/
    private List<Goods> goodsList;
}
