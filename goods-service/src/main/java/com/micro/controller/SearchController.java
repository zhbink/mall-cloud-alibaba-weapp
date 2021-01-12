package com.micro.controller;

import com.micro.dto.CommonResult;
import com.micro.service.SearchService;
import com.micro.mbg.model.Goods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author nnkwrik
 * @date 18/11/18 21:17
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {


//    @Autowired
//    private SearchCache searchCache;


    private final SearchService searchService;


    /**
     * 搜索页,展示热门关键字和当前用户的搜索历史
     *
     * @param jwtUser
     * @return
     */
//    @GetMapping("/index")
//    public Response<SearchPageVo> searchIndex(@JWT JWTUser jwtUser) {
//        List<String> historyKeyword = null;
//        String openId = null;
//        if (jwtUser != null) {
//            openId = jwtUser.getOpenId();
//            historyKeyword = searchService.getUserHistory(openId);
//        }
//
//        List<String> hotKeyword = searchCache.getHot(10);
//        SearchPageVo vo = new SearchPageVo(historyKeyword, hotKeyword);
//        log.info("用户openId= 【{}】获取搜索历史和热门关键词，搜索历史 = 【{}】，热门关键词 = 【{}】", openId, historyKeyword, hotKeyword);
//
//        return Response.ok(vo);
//    }

    /**
     * 清空搜索历史
     *
     * @param jwtUser
     * @return
     */
//    @GetMapping("/clearhistory")
//    public Response clearHistory(@JWT(required = true) JWTUser jwtUser) {
//        searchService.clearUserHistory(jwtUser.getOpenId());
//        log.info("用户openId= 【{}】清空搜索历史", jwtUser.getOpenId());
//        return Response.ok();
//    }

    /**
     * 获取搜索结果,并把关键字加入SearchCache
     *
     * @param keyword 搜索的关键字,可以用空格分割多个关键字
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/result/{keyword}")
    public CommonResult<List<Goods>> searchGoods(@PathVariable("keyword") String keyword,
                                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size) {
        List<String> keywordList = Arrays.asList(StringUtils.split(keyword));
        List<Goods> goodsListVo = searchService.searchByKeyword(keywordList, page, size);

//        //数据库改openid的搜索历史
//        String openId = null;
//        if (jwtUser != null) {
//            openId = jwtUser.getOpenId();
//            searchService.updateUserHistory(openId, keyword);
//        }

        //加入热门搜索缓存
//        keywordList.stream()
//                .forEach(word -> searchCache.add(word.toLowerCase()));

//        log.debug("用户 openid=【{}】，通过关键字【{}】搜索商品，搜索结果:{}", openId, keyword, goodsListVo);
        return new CommonResult(goodsListVo);
    }


}