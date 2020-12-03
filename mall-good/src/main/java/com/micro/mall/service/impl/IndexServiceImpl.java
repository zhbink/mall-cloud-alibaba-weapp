package com.micro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.mall.dto.CatalogPageVo;
import com.micro.mall.dto.IndexPageVo;
import com.micro.mbg.mapper.CategoryMapper;
import com.micro.mbg.mapper.ChannelMapper;
import com.micro.mbg.mapper.GoodsMapper;
import com.micro.mbg.mapper.IndexMapper;
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

    private final ChannelMapper channelMapper;
    private final IndexMapper indexMapper;
    private final GoodsMapper goodsMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public IndexPageVo getIndex(int page, int size) {
        //广告
        List<Ad> adList = indexMapper.findAd();

        //分类
        List<Channel> channelList = indexMapper.findChannel();

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
        Example example = new Example(Category.class);
        example.selectProperties("id", "name");
        example.and().andEqualTo("parentId", 0);
        example.orderBy("sortOrder").asc();
        List<Category> allCategory = categoryMapper.selectByExample(example);
//        上面代码相当于下面的方法：
//        List<Category> allCategory = categoryMapper.findMainCategory();

        //获得第一个主分类下的所有子分类
        Example example2 = new Example(Category.class);
        example2.selectProperties("id", "name", "iconUrl");
        example2.and().andEqualTo("parentId", allCategory.get(0));
        example2.orderBy("sortOrder").asc();
        List<Category> subCategory = categoryMapper.selectByExample(example2);
//        List<Category> subCategory = categoryMapper.findSubCategory(topCategory.getId());
        return new CatalogPageVo(allCategory, subCategory);
    }

    @Override
    public List<Category> getSubCatalogById(int id) {
        //主分类=id下的所有子分类
        return categoryMapper.findSubCategory(id);
    }

    @Override
    public List<Goods> goodsTest() {
        return goodsMapper.selectAll();
    }



}
