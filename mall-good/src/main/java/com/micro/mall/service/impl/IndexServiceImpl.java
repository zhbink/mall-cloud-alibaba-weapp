package com.micro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.mall.dto.CatalogPageVo;
import com.micro.mall.dto.IndexPageVo;
import com.micro.mbg.mapper.*;
import com.micro.mbg.model.Ad;
import com.micro.mbg.model.Category;
import com.micro.mbg.model.Channel;
import com.micro.mbg.model.Goods;
import com.micro.mall.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class IndexServiceImpl implements IndexService {

    private final AdMapper adMapper;
    private final ChannelMapper channelMapper;
    private final IndexMapper indexMapper;
    private final GoodsMapper goodsMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public IndexPageVo getIndex(int page, int size) {
        //广告
        Example adExample = new Example(Ad.class);
        adExample.selectProperties("id","name","imageUrl","link");
        adExample.and().andEqualTo("enable", true);
        adExample.orderBy("sortOrder").asc();
        List<Ad> adList = adMapper.selectByExample(adExample);
//        List<Ad> adList = indexMapper.findAd();

        //分类
        Example channelExample = new Example(Channel.class);
        channelExample.selectProperties("id", "name", "url", "iconUrl");
        channelExample.orderBy("sortOrder").asc();
        List<Channel> channelList = channelMapper.selectByExample(channelExample);
//        List<Channel> channelList = indexMapper.findChannel();


        //推荐商品
        PageInfo<Goods> goodsList = getIndexMore(page, size);

        return new IndexPageVo(goodsList, adList, channelList);
    }

    @Override
    public PageInfo<Goods> getIndexMore(int page, int size) {
        PageHelper.startPage(page, size);
        List<Goods> allGoods = goodsMapper.selectAll();
        PageInfo<Goods> pageInfo = new PageInfo<>(allGoods);
        return pageInfo;
    }

    @Override
    public CatalogPageVo getCatalogIndex() {
        //所有主分类
        Example mainCatExample = new Example(Category.class);
        mainCatExample.selectProperties("id", "name");
        mainCatExample.and().andEqualTo("parentId", 0);
        mainCatExample.orderBy("sortOrder").asc();
        List<Category> allCategory = categoryMapper.selectByExample(mainCatExample);
//        上面代码相当于下面的方法：
//        List<Category> allCategory = categoryMapper.findMainCategory();

        Category topCategory = allCategory.get(0);

        //获得第一个主分类下的所有子分类
        Example subCatExample = new Example(Category.class);
        subCatExample.selectProperties("id", "name", "iconUrl");
        subCatExample.and().andEqualTo("parentId", allCategory.get(0).getId());
        subCatExample.orderBy("sortOrder").asc();
        List<Category> subCategory = categoryMapper.selectByExample(subCatExample);
//        List<Category> subCategory = categoryMapper.findSubCategory(topCategory.getId());
        return new CatalogPageVo(allCategory, subCategory);
    }

    @Override
    public List<Category> getSubCatalogById(int id) {
//        主分类=id下的所有子分类
        Category mainCategory = categoryMapper.selectByPrimaryKey(id);

        Example subCatExample = new Example(Category.class);
        subCatExample.selectProperties("id", "name", "iconUrl");
        subCatExample.and().andEqualTo("parentId",id);
        subCatExample.orderBy("sortOrder").asc();
        List<Category> subCategory = categoryMapper.selectByExample(subCatExample);
        return subCategory;
//        return categoryMapper.findSubCategory(id);
    }

    @Override
    public List<Goods> goodsTest() {
        return goodsMapper.selectAll();
    }



}
