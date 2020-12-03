package com.micro.mall.controller;

import com.github.pagehelper.PageInfo;
import com.micro.mall.dto.CatalogPageVo;
import com.micro.mall.dto.CommonResult;
import com.micro.mall.dto.IndexPageVo;
import com.micro.mall.service.IndexService;
import com.micro.mbg.mapper.GoodsMapper;
import com.micro.mbg.model.Goods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@RestController
public class IndexController {
    private final IndexService indexService;
    private final GoodsMapper goodsMapper;

    @GetMapping("/index/index")
    public CommonResult<IndexPageVo> index(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "4") int size) {


        IndexPageVo vo = indexService.getIndex(page, size);
//        log.info("浏览首页 : 展示{}个广告, {}个分类, {}个商品", vo.getBanner().size(), vo.getChannel().size(), vo.getIndexGoodsList().size());
        return new CommonResult(vo);
    }

    @GetMapping("/index/more")
    public CommonResult indexMore(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "4") int size) {
        PageInfo<Goods> goodsList = indexService.getIndexMore(page, size);
//        log.info("首页获取更多推荐商品 : 返回{}个商品", goodsList);
        return new CommonResult(goodsList);
    }

    /**
     * 分类页,展示:所有主分类,排名第一的主分类包含的子分类
     *
     * @return
     */
    @GetMapping("/catalog/index")
    public CommonResult catalog() {

        CatalogPageVo vo = indexService.getCatalogIndex();
        log.info("浏览分类页 : 展示{}个主分类, 展示{}个子分类", vo.getAllCategory().size(), vo.getSubCategory().size());

        return new CommonResult(vo);
    }


}
