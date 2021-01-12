package com.micro.controller;

import com.micro.dto.CommonResult;
import com.micro.service.GoodsUserService;
import com.micro.service.UserInfoService;
import com.micro.mbg.model.Goods;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("goodsUser")
public class GoodsUserController {
    private final GoodsUserService goodsUserService;
    private final UserInfoService userInfoService;

    @GetMapping("posted")
    public CommonResult getPosted(@RequestParam Integer userId,
                                  @RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        return new CommonResult(goodsUserService.getPosted(userId, page, size));
    }

    @PostMapping("/collect/addordelete/{goodsId}/{userHasCollect}/{userId}")
    public CommonResult collectAddOrDelete(@PathVariable("goodsId") int goodsId,
                                           @PathVariable("userHasCollect") int userHasCollect,
                                           @PathVariable("userId") int userId) {
        boolean hasCollect1 = userHasCollect==1?true:false;
        userInfoService.collectAddOrDelete(goodsId, userId, hasCollect1);
        return new CommonResult();

    }

    /**
     * 获取用户收藏的商品列表
     *
     * @param user
     * @return
     */
    @GetMapping("/collect")
    public CommonResult<List<Goods>> getCollectList(@RequestParam Integer userId,
                                                @RequestParam(value = "page", defaultValue = "1") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Goods> vo = userInfoService.getUserCollectList(userId, page, size);
        return new CommonResult(vo);
    }

    /**
     * 获取用户买过的商品列表
     *
     * @param user
     * @return
     */
    @GetMapping("/bought")
    public CommonResult<List<Goods>> getUserBought(@RequestParam Integer userId,
                                               @RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Goods> vo = userInfoService.getUserBought(userId, page, size);

        return new CommonResult(vo);
    }

    /**
     * 获取用户卖出的商品列表
     *
     * @param user
     * @return
     */
    @GetMapping("/sold")
    public CommonResult<List<Goods>> getUserSold(@RequestParam Integer userId,
                                             @RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Goods> vo = userInfoService.getUserSold(userId, page, size);

        return new CommonResult(vo);
    }

}
