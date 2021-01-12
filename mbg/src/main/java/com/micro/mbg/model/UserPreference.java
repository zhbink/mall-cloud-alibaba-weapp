package com.micro.mbg.model;

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
@Table(name = "user_preference")
public class UserPreference {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "user_id")
    private String userId;

    /**
     * 1:收藏,2:想要
     */
    private Integer type;

    @Column(name = "create_time")
    private Date createTime;
}