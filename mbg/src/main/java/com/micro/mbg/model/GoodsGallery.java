package com.micro.mbg.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "goods_gallery")
public class GoodsGallery {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "img_url")
    private String imgUrl;
}