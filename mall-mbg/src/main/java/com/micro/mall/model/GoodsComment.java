package com.micro.mall.model;

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
@Table(name = "goods_comment")
public class GoodsComment {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "reply_comment_id")
    private Integer replyCommentId;

    @Column(name = "reply_user_id")
    private String replyUserId;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "create_time")
    private Date createTime;

    private String content;
}