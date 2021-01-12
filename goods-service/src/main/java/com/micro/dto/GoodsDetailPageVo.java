package com.micro.dto;

import com.micro.mbg.model.Goods;
import com.micro.mbg.model.GoodsGallery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商品详情页
 *
 * @author nnkwrik
 * @date 18/11/17 21:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailPageVo {

    /*商品详情*/
    private Goods info;
    /*商品图片*/
    private List<GoodsGallery> gallery;

    /*卖家信息*/
    private SimpleUser seller;
    /*卖家出售过的商品数*/
    private Integer sellerHistory;

    /*评论*/
    private List<CommentVo> comment;
    /*用户是否收藏*/
    private Boolean userHasCollect;
}
