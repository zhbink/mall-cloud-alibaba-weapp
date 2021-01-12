package com.micro.service;

import com.micro.mbg.model.Goods;

import java.util.LinkedHashMap;
import java.util.List;

public interface UserInfoService {
    Boolean userHasCollect(int userId, int goodsId);

    void collectAddOrDelete(int goodsId, int userId, boolean hasCollect);

    List<Goods> getUserCollectList(int userId, int page, int size);

    List<Goods> getUserBought(int buyerId, int page, int size);

    List<Goods> getUserSold(int sellerId, int page, int size);

//    LinkedHashMap<String, List<Goods>> getUserHistoryList(String userId, int page, int size);


}
