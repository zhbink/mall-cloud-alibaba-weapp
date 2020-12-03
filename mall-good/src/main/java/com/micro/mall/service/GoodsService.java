package com.micro.mall.service;

import com.micro.mall.dto.CategoryPageVo;
import com.micro.mall.dto.CommentVo;
import com.micro.mbg.model.Goods;
import com.micro.mbg.model.GoodsGallery;

import java.util.List;

public interface GoodsService {

    CategoryPageVo getGoodsAndBrotherCateById(int id, int page, int size);

    List<Goods> getGoodsByCateId(int id, int page, int size);

    Goods getGoodsDetail(int goodsId);

    int getSellerHistory(String sellerId);

    List<GoodsGallery> getGoodsGallery(int goodsId);

    List<Goods> getGoodsRelated(int goodsId, int page, int size);

    List<CommentVo> getGoodsComment(int goodsId);

    void addComment(int goodsId, String userId, int replyCommentId, String replyUserId, String content);
}
