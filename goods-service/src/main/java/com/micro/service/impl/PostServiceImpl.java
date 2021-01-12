package com.micro.service.impl;

import com.micro.dto.PostExample;
import com.micro.service.PostService;
import com.micro.mbg.mapper.CategoryMapper;
import com.micro.mbg.mapper.GoodsMapper;
import com.micro.mbg.mapper.RegionMapper;
import com.micro.mbg.model.Category;
import com.micro.mbg.model.GoodsGallery;
import com.micro.mbg.model.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final RegionMapper regionMapper;
    private final CategoryMapper categoryMapper;
    private final GoodsMapper goodsMapper;

    @Override
    @Transactional
    public void postGoods(PostExample post) {
        List<String> images = post.getImages();
        List<GoodsGallery> galleryList = new ArrayList<>();
        post.setPrimaryPicUrl(images.get(0)); //TODO 对PrimaryImage进行压缩

        //添加到goods表并获取id

        goodsMapper.addGoods(post);
        int goodsId = post.getId();

        //把照片添加到Gallery表
        images.stream()
                .forEach(url -> {
                    GoodsGallery gallery = new GoodsGallery();
                    gallery.setGoodsId(goodsId);
                    gallery.setImgUrl(url);
                    galleryList.add(gallery);
                });
        goodsMapper.addGalleryList(galleryList);
    }


    @Override
    public List<Region> getRegionList(int regionId) {
        Example example = new Example(Region.class);
        example.selectProperties("id","name");
        example.and().andEqualTo("parentId", regionId);
        return regionMapper.selectByExample(example);
    }

    @Override
    public List<Category> getCateList(int cateId) {
        Example example = new Example(Category.class);
        example.selectProperties("id","name");
        example.and().andEqualTo("parentId", cateId);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public void deleteGoods(int goodId){
        goodsMapper.deleteByPrimaryKey(goodId);
    }
}
