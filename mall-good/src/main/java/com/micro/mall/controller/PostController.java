package com.micro.mall.controller;
import com.micro.mall.dto.CommonResult;
import com.micro.mall.dto.PostExample;
import com.micro.mall.service.FeignUserService;
import com.micro.mall.service.PostService;
import com.micro.mbg.model.Category;
import com.micro.mbg.model.Region;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final FeignUserService feignUserService;
    @PostMapping("/post")
    public CommonResult postGoods(@RequestBody PostExample post) {

//        if (StringUtils.isEmpty(post.getName()) ||
//                StringUtils.isEmpty(post.getDescription()) ||
//                StringUtils.isEmpty(post.getRegion()) ||
//                post.getCategoryId() == null ||
//                post.getRegionId() == null ||
//                post.getPrice() == null ||
//                post.getUserId() == null ||
//                post.getImages() == null || post.getImages().size() < 1) {
//            String msg = "用户发布商品失败，信息不完整";
//            log.info(msg);
//            return new CommonResult(CommonResult.POST_INFO_INCOMPLETE, msg);
//        }

        post.setSellerId(post.getUserId());
        postService.postGoods(post);

        return new CommonResult("post success");
    }

    /**
     * 删除已发布的商品
     *
     * @param goodsId
     * @return
     */
    @DeleteMapping("/delete/{goodsId}")
    public CommonResult deleteGoods(@PathVariable int goodsId) {
        try {
            postService.deleteGoods(goodsId);
        } catch (Exception e) {
            if (e.getMessage().equals(CommonResult.SELLER_AND_GOODS_IS_NOT_MATCH + "")) {
                String msg = "删除商品失败.当前用户信息和卖家信息不匹配";
                return new CommonResult(CommonResult.SELLER_AND_GOODS_IS_NOT_MATCH, msg);
            }
            e.printStackTrace();

        }
        return new CommonResult("删除成功");
    }

    /**
     * 获取发布商品时需要填选的发货地区
     *
     * @param regionId
     * @return
     */
    @GetMapping("/region/{regionId}")
    public CommonResult getRegionList(@PathVariable("regionId") int regionId) {
        List<Region> regionList = postService.getRegionList(regionId);
        log.info("通过地区id=【{}】，搜索地区子列表。搜索到{}个结果", regionId, regionList.size());
        return new CommonResult(regionList);

    }

    /**
     * 获取发布商品时需要填选的分类
     *
     * @param cateId
     * @return
     */
    @GetMapping("/category/{cateId}")
    public CommonResult getPostCateList(@PathVariable("cateId") int cateId) {
        List<Category> cateList = postService.getCateList(cateId);
        log.info("通过分类id=【{}】，搜索分类子列表。搜索到{}个结果", cateId, cateList.size());
        return new CommonResult(cateList);
    }
}
