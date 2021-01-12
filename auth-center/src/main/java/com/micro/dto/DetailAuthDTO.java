package com.micro.dto;

import lombok.Data;


@Data
public class DetailAuthDTO {
    public String errMsg;
    public String signature;
    public String rawData; //不包括敏感信息的原始数据字符串，用于计算签名
    public String encryptedData;  //包括敏感数据在内的完整用户信息的加密数据
    public String iv;  //加密算法的初始向量
}