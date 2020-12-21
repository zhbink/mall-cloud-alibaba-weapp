package com.micro.mall.dto;

import com.micro.mbg.model.Goods;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostExample extends Goods {
    private List<String> images;
    private Integer userId;
}