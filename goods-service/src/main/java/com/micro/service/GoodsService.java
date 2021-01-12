package com.micro.service;

import com.micro.dto.CategoryPageVo;
import com.micro.dto.CommentVo;
import com.micro.mbg.model.Goods;
import com.micro.mbg.model.GoodsGallery;

import java.util.List;

public interface GoodsService {

    CategoryPageVo getGoodsAndBrotherCateById(int id, int page, int size);

    List<Goods> getGoodsByCateId(int id, int page, int size);

    Goods getGoodsDetail(int goodsId);

    Integer getSellerHistory(int sellerId);

    List<GoodsGallery> getGoodsGallery(int goodsId);

    List<Goods> getGoodsRelated(int goodsId, int page, int size);

    List<CommentVo> getGoodsComment(int goodsId);

    void addComment(int goodsId, int userId, int replyCommentId, String replyUserId, String content);
}
