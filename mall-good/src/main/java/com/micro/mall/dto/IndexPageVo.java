package com.micro.mall.dto;

import com.github.pagehelper.PageInfo;
import com.micro.mbg.model.Ad;
import com.micro.mbg.model.Channel;
import com.micro.mbg.model.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexPageVo {

    /*首页推荐商品*/
    private PageInfo<Goods> indexGoodsList;

    /*广告banner*/
    private List<Ad> banner;

    /*首页展示分类*/
    private List<Channel> channel;
}