package com.micro.dto;

import com.micro.mbg.model.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageVo {

    /*用户基本信息*/
    private SimpleUser user;

    /*用户历史*/
    private LinkedHashMap<String, List<Goods>> userHistory;

    /*出售过几件商品*/
    private Integer soldCount;
}
