package com.cs.shop.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: csong
 * @create: 2019-12-04
 **/
@Data
public class CarOwner implements Serializable {
    int id;//编号
    String nickName;//昵称
    String name;//真实姓名
    String tel1;//联系方式1
    String tel2;//联系方式2
    String qq;//QQ号码
    String wx;//微信号码
    String email;//邮箱
    String country;//国家
    String province;//省份
    String city;//城市
    String address;//详细地址
    String value1;//备注
}
