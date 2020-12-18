package com.micro.mbg.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "buyer_id")
    private Integer buyerId;

    private String name;

    private BigDecimal price;

    @Column(name = "market_price")
    private BigDecimal marketPrice;

    private BigDecimal postage;

    @Column(name = "primary_pic_url")
    private String primaryPicUrl;

    @Column(name = "region_id")
    private Integer regionId;

    private String region;

    @Column(name = "want_count")
    private Integer wantCount;

    @Column(name = "browse_count")
    private Integer browseCount;

    @Column(name = "is_selling")
    private Boolean isSelling;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "able_express")
    private Boolean ableExpress;

    @Column(name = "able_meet")
    private Boolean ableMeet;

    @Column(name = "able_self_take")
    private Boolean ableSelfTake;

    @Column(name = "post_time")
    private Date postTime;

    @Column(name = "last_edit")
    private Date lastEdit;

    @Column(name = "sold_time")
    private Date soldTime;

    private String description;
}