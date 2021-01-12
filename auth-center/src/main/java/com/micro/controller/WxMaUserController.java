package com.micro.controller;

import com.micro.dto.AuthDTO;
import com.micro.mbg.mapper.UserMapper;
import com.micro.mbg.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.micro.config.WxMaConfiguration;
import com.micro.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequiredArgsConstructor
//@RequestMapping("/wx/user/{appid}")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserMapper userMapper;

    /**
     * 登陆接口
     */
    @GetMapping("/hi")
    public String test() {
        return "test success";
    }

    @Value("${appid}")
    private String appid;

    @PostMapping("/loginByWeixin")
    public String login(@RequestBody AuthDTO authDTO) {
        this.logger.info(appid);
        String code = authDTO.getCode();
//        if (StringUtils.isBlank(code)) {
//            return "empty jscode";
//        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
//            this.logger.info(session.getSessionKey());
//            this.logger.info(session.getOpenid());
//            String token = session.getOpenid();


            String sessionKey = session.getSessionKey();
            String encryptedData = authDTO.getDetail().getEncryptedData();
            String iv = authDTO.getDetail().getIv();
            WxMaUserInfo userInfoBefore = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("openId", userInfoBefore.getOpenId());
            if (userMapper.selectByExample(example).size()==0) {
                logger.debug("\n【新用户登陆】：将用户写入数据库...");
                User user = User.builder()
                        .openId(userInfoBefore.getOpenId())
                        .nickName(userInfoBefore.getNickName())
                        .avatarUrl(userInfoBefore.getAvatarUrl())
                        .gender(Integer.parseInt(userInfoBefore.getGender()))
                        .country(userInfoBefore.getCountry())
                        .province(userInfoBefore.getProvince())
                        .city(userInfoBefore.getCity())
                        .language(userInfoBefore.getLanguage())
                        .registerTime(new Date())
                        .build();
                userMapper.insertSelective(user);
                logger.debug("\n【新用户登陆】：数据插入成功。");
            }
            String userInfo = JsonUtils.toJson(userInfoBefore);

            return userInfo;
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public String info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    public String phone(@PathVariable String appid, String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(phoneNoInfo);
    }

}
