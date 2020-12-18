package com.micro.mall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUser {
    private String openId;
    private String nickName;
    private String avatarUrl;

    @JsonFormat(pattern = StdDateFormat.DATE_FORMAT_STR_ISO8601)
    private Date registerTime;

    public static SimpleUser unknownUser() {
        SimpleUser unknownUser = new SimpleUser();
        unknownUser.setNickName("用户不存在");
        unknownUser.setAvatarUrl("https://i.postimg.cc/RVbDV5fN/anonymous.png");
        return unknownUser;
    }
}
