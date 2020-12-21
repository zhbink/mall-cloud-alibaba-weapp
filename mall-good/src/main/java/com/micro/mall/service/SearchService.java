package com.micro.mall.service;

import com.micro.mbg.model.Goods;

import java.util.List;

/**
 * @author nnkwrik
 * @date 18/11/21 9:17
 */
public interface SearchService {

    List<Goods> searchByKeyword(List<String> keywordList, int page, int size);

//    List<String> getUserHistory(String openId);
//
//    void updateUserHistory(String openId, String keyword);
//
//    void clearUserHistory(String openId);
}
