package com.micro.mall.controller;

import com.micro.mall.dto.*;
import com.micro.mall.service.GoodsService;
import com.micro.mbg.model.Goods;
import com.micro.mbg.model.GoodsComment;
import com.micro.mbg.model.GoodsGallery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsController {

    private final GoodsService goodsService;
//    private final UserService userService;
//    private final BrowseCache browseCache;
//    private final UserClientHandler userClientHandler;

    /**
     * 通过分类浏览商品,获取选定目录下的商品列表和同级的兄弟目录
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/category/index/{categoryId}")

    public CommonResult getCategoryPage(@PathVariable("categoryId") int categoryId,
                                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                                    @RequestParam(value = "size", defaultValue = "10") int size) {


        CategoryPageVo vo = goodsService.getGoodsAndBrotherCateById(categoryId, page, size);
        log.info("通过分类浏览商品 : 分类id = {},展示{}个商品", categoryId, vo.getGoodsList().size());

        return new CommonResult(vo);
    }

    /**
     * 通过分类浏览商品,获取选定目录下的商品列表
     *
     * @param categoryId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/category/{categoryId}")
    public CommonResult  getGoodsByCategory(@PathVariable("categoryId") int categoryId,
                                              @RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size) {

        List<Goods> goodsList = goodsService.getGoodsByCateId(categoryId, page, size);
        log.info("通过分类浏览商品 : 分类id = {},展示{}个商品", categoryId, goodsList.size());
        return  new CommonResult(goodsList);

    }

    /**
     * 获取商品的详细信息,包括:商品信息,商品图片,商品评论,卖家信息,用户是否收藏了该商品
     *
     * @param goodsId
     * @param jwtUser
     * @return
     */
//    @GetMapping("/detail/{goodsId}")
//    public CommonResult getGoodsDetail(@PathVariable("goodsId") int goodsId,
//                                                      @JWT JWTUser jwtUser) {
//        //更新浏览次数
//        browseCache.add(goodsId);
//        //获取商品详情
//        Goods goods = goodsService.getGoodsDetail(goodsId);
//        //获取买家信息
//        SimpleUser seller = userClientHandler.getSimpleUser(goods.getSellerId());
//        if (seller == null) {
//            log.info("搜索goodsId = 【{}】的详情时出错", goodsId);
//            return new CommonResult(2001, "无法搜索到商品卖家的信息");
//        }
//        //卖家出售过的商品数
//        int sellerHistory = goodsService.getSellerHistory(goods.getSellerId());
//
//        List<GoodsGallery> goodsGallery = goodsService.getGoodsGallery(goodsId);
//        List<CommentVo> comment = goodsService.getGoodsComment(goodsId);
//
//        //用户是否收藏
//        boolean userHasCollect = false;
//        if (jwtUser != null)
//            userHasCollect = userService.userHasCollect(jwtUser.getOpenId(), goodsId);
//
//        GoodsDetailPageVo vo = new GoodsDetailPageVo(goods, goodsGallery, seller, sellerHistory, comment, userHasCollect);
//        log.info("浏览商品详情 : 商品id={}，商品名={}", vo.getInfo().getId(), vo.getInfo().getName());
//
//        return  new CommonResult(vo);
//    }

    /**
     * 获取与id商品相关的商品
     *
     * @param goodsId
     * @return
     */
    @GetMapping("/related/{goodsId}")
    public CommonResult getGoodsRelated(@PathVariable("goodsId") int goodsId,
                                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Goods> goodsList = goodsService.getGoodsRelated(goodsId, page, size);
        log.info("获取与 goodsId=[{}] 相关的商品 : 展示{}个商品", goodsId, goodsList.size());

        return  new CommonResult(goodsList);
    }

    /**
     * 发表评论
     *
     * @param goodsId
     * @param comment
     * @param user
     * @return
     */
//    @PostMapping("/comment/post/{goodsId}")
//    public Response postComment(@PathVariable("goodsId") int goodsId,
//                                @RequestBody GoodsComment comment,
//                                @JWT(required = true) JWTUser user) {
//        if (StringUtils.isEmpty(user.getOpenId()) ||
//                StringUtils.isEmpty(comment.getReplyUserId()) ||
//                StringUtils.isEmpty(comment.getContent()) ||
//                comment.getReplyCommentId() == null) {
//            String msg = "用户发表评论失败，信息不完整";
//            log.info(msg);
//            return Response.fail(Response.COMMENT_INFO_INCOMPLETE, msg);
//        }
//
//        goodsService.addComment(goodsId, user.getOpenId(), comment.getReplyCommentId(), comment.getReplyUserId(), comment.getContent());
//
//        log.info("用户添加评论：用户昵称=【{}】，回复评论id=【{}】，回复内容=【{}】", user.getNickName(), comment.getReplyCommentId(), comment.getContent());
//        return Response.ok();
//
//    }


}
