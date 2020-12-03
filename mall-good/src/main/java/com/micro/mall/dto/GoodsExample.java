package com.micro.mall.dto;

import com.micro.mbg.model.Goods;
import lombok.Data;

import java.util.Date;


@Data
public class GoodsExample extends Goods {
    private Date time;
}
