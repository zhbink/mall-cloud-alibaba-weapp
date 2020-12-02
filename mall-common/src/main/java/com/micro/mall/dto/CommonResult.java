package com.micro.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    //auth
    public static final int WRONG_JS_CODE = 3001;
    public static final int CHECK_USER_WITH_SESSION_FAIL = 3002;
    public static final int TOKEN_IS_EMPTY = 3003;
    public static final int TOKEN_IS_EXPIRED = 3004;
    public static final int TOKEN_IS_WRONG = 3005;

    //goods
    public static final int OPEN_ID_IS_EMPTY = 4001;
    public static final int COMMENT_INFO_INCOMPLETE = 4002;
    public static final int POST_INFO_INCOMPLETE = 4003;
    public static final int SELLER_AND_GOODS_IS_NOT_MATCH = 4004;
    public static final int GOODS_IN_NOT_EXIST = 4005;

    //user
    public static final int USER_IS_NOT_EXIST = 2001;

    //im
    public static final int MESSAGE_FORMAT_IS_WRONG = 5001;
    public static final int MESSAGE_IS_INCOMPLETE = 5002;
    public static final int SENDER_AND_WS_IS_NOT_MATCH = 5003;
    public static final int UPDATE_HISTORY_TO_SQL_FAIL = 5004;

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

}
