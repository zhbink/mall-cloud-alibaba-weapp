package com.micro.service.impl;

import com.github.pagehelper.PageHelper;
import com.micro.dao.UserInfoMapper;
import com.micro.service.UserInfoService;
import com.micro.mbg.mapper.GoodsMapper;
import com.micro.mbg.model.Goods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoMapper userInfoMapper;
    private final GoodsMapper goodsMapper;

    @Override
    public Boolean userHasCollect(int userId, int goodsId) {
        return userInfoMapper.userHasCollect(userId, goodsId);
    }

    @Override
    public void collectAddOrDelete(int goodsId, int userId, boolean hasCollect) {
        if (hasCollect) {
            userInfoMapper.deleteUserCollect(userId, goodsId);
        } else {
            userInfoMapper.setUserCollect(userId, goodsId);
        }
    }

    @Override
    public List<Goods> getUserCollectList(int userId, int page, int size) {
        PageHelper.startPage(page, size);
        List<Goods> res1 = userInfoMapper.getUserCollect(userId);
        for (Goods res: res1) {
            Goods good = goodsMapper.selectByPrimaryKey(res.getId());
            res.setPrimaryPicUrl(good.getPrimaryPicUrl());
            res.setIsSelling(good.getIsSelling());
        }
        return res1;
    }

    @Override
    public List<Goods> getUserBought(int buyerId, int page, int size) {
        PageHelper.startPage(page, size);
        List<Goods> res1 = userInfoMapper.getUserBought(buyerId);
        for (Goods res: res1) {
            Goods good = goodsMapper.selectByPrimaryKey(res.getId());
            res.setPrimaryPicUrl(good.getPrimaryPicUrl());
            res.setSoldTime(good.getSoldTime());
        }
        return res1;
    }

    @Override
    public List<Goods> getUserSold(int sellerId, int page, int size) {
        PageHelper.startPage(page, size);
        List<Goods> res1 = userInfoMapper.getUserSold(sellerId);
        for (Goods res: res1) {
            Goods good = goodsMapper.selectByPrimaryKey(res.getId());
            res.setPrimaryPicUrl(good.getPrimaryPicUrl());
            res.setSoldTime(good.getSoldTime());
        }
        return res1;
    }


    private LocalDate getDay(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private String dateFormat(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = localDate.format(formatter);
        return formattedString;
    }
}
