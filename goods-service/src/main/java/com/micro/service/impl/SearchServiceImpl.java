package com.micro.service.impl;

import com.github.pagehelper.PageHelper;
import com.micro.service.SearchService;
import com.micro.mbg.mapper.GoodsMapper;
import com.micro.mbg.mapper.SearchMapper;
import com.micro.mbg.model.Goods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {


    private final SearchMapper searchMapper;
    private final GoodsMapper goodsMapper;


    @Override
    public List<Goods> searchByKeyword(List<String> keywordList, int page, int size) {
        PageHelper.startPage(page, size);
        List<Goods> res1 = searchMapper.findGoodsByKeywords(keywordList);
        for (Goods res: res1) {
            Goods good = goodsMapper.selectByPrimaryKey(res.getId());
            res.setPrimaryPicUrl(good.getPrimaryPicUrl());
        }
        return res1;
    }

//    @Override
//    public List<String> getUserHistory(String openId) {
//        List<SearchHistory> historyPo = searchMapper.findSearchHistory(openId);
//        int limit = 10;
//        if (historyPo.size() >= limit) {//或者超过一定数量就删除
//            searchMapper.deleteOldHistory(openId, historyPo.size() - limit);
//        }
//        return historyPo.stream()
//                .map(SearchHistory::getKeyword)
//                .collect(Collectors.toList());
//    }

//    @Override
//    @Transactional
//    public void updateUserHistory(String openId, String keyword) {
//        if (searchMapper.isExistedHistory(openId, keyword)) {
//            //之前搜索过,更新搜索时间
//            searchMapper.updateSearchTime(openId, keyword);
//        } else {
//            searchMapper.insertHistory(openId, keyword);
//        }
//    }
//
//    @Override
//    public void clearUserHistory(String openId) {
//        searchMapper.clearHistory(openId);
//    }
}