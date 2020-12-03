package com.micro.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPageVo {

    /*搜索历史*/
    private List<String> historyKeywordList;

    /*热门关键字*/
    private List<String> hotKeywordList;
}