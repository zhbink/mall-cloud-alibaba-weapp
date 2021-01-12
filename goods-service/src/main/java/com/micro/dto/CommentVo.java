package com.micro.dto;

import com.micro.mbg.model.GoodsComment;
import lombok.Data;

import java.util.List;


@Data
public class CommentVo extends GoodsComment {

    /*评论用户信息*/
    private SimpleUser simpleUser;

    /*回复的用户昵称*/
    private String replyUserName;

    /*回复该评论的评论列表*/
    private List<CommentVo> replyList;
}